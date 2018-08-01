package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.Doctor;
import model.DoctorVacation;
import model.Person;
import network.api.DoctorAPI;
import network.api.ProfileAPI;
import network.api.VacationAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.AutoSignIn;
import utils.Response;
import view.forms.DoctorVacationForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

public class DoctorVacationTableView extends GenericTableView<DoctorVacation> {

    VacationAPI api ;
    List<DoctorVacation> vacations = new ArrayList<>();
    Map<String,String> names = new HashMap<>();

    public DoctorVacationTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        DoctorVacation vacation = vacations.remove(index);
        api.delete(vacation,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<DoctorVacation> onView(int index) {
        DoctorVacation vacation = vacations.get(index);
        return new DoctorVacationForm(vacation,this);
    }

    @Override
    protected UIFormView<DoctorVacation> onInsert() {
        return new DoctorVacationForm(null, this);
    }

    @Override
    protected Class<DoctorVacation> classType() {
        return DoctorVacation.class;
    }


    @Override
    public Collection<? extends DoctorVacation> dataSource() {
        return vacations;
    }

    @Override
    public void callback(DoctorVacation value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public TableColumnValue<DoctorVacation> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return p-> names.get(p.getDoctorID());

                default:return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new VacationAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {

            if(response.isOK()) {
                new DoctorAPI().readAll((response12, items12) -> {
                    Set<String> validDoctors = new HashSet<>();

                    for (Doctor doctor : items12) {
                        if(AutoSignIn.ROLE_ID == 6 || AutoSignIn.HOSPITAL_ID.equals(doctor.getHospitalID()))
                            validDoctors.add(doctor.getDoctorID());
                    }

                    new ProfileAPI().readAll((response1, items1) -> {

                        for (Person profile : items1){
                            names.put(profile.getID(),profile.getFirstName() +" "+profile.getSurName());
                        }
                        vacations.clear();

                        for (DoctorVacation item : items) {
                            if(validDoctors.contains(item.getDoctorID()))
                                vacations.add(item);
                        }
                        reloadData();
                    });
                });


            }
        });
    }
}
