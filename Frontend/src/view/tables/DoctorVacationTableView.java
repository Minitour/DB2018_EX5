package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.DoctorVacation;
import network.api.VacationAPI;
import view.forms.DoctorVacationForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorVacationTableView extends GenericTableView<DoctorVacation> {

    VacationAPI api ;
    List<DoctorVacation> vacations = new ArrayList<>();

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
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new VacationAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            vacations.clear();
            vacations.addAll(items);
            reloadData();
        });
    }
}
