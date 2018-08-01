package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.*;
import network.api.*;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.AutoSignIn;
import utils.Response;
import view.forms.DoctorForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

public class DoctorsTableView extends GenericTableView<Doctor> {

    private DoctorAPI doctorAPI;
    private List<Doctor> doctorList = new ArrayList<>();

    private Map<String, String> names;
    private Map<Integer, Hospital> hospitalMap;
    private Map<String, Department> departmentMap;


    public DoctorsTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Doctor doctor = doctorList.remove(index);
        doctorAPI.delete(doctor,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Doctor> onView(int index) {
        Doctor doctor = doctorList.get(index);
        return new DoctorForm(doctor,this);
    }

    @Override
    protected UIFormView<Doctor> onInsert() {
        return new DoctorForm(null, this);
    }

    @Override
    protected Class<Doctor> classType() {
        return Doctor.class;
    }

    @Override
    public Collection<? extends Doctor> dataSource() {
        return doctorList;
    }



    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        names = new HashMap<>();
        hospitalMap = new HashMap<>();
        departmentMap = new HashMap<>();

        doctorAPI = new DoctorAPI();

        doctorAPI.setRunOnUi(true);
        reloadDataFromServer();
    }

    @Override
    public void callback(Doctor value) {
        super.callback(value);
        //on update or insert
        doctorAPI.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public TableColumnValue<Doctor> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return p -> names.get(p.getDoctorID());
            case 1:
                return p -> hospitalMap.get(p.getHospitalID()).getName();
            case 2:
                return p -> departmentMap.get(p.getHospitalID() + "_"+p.getDepartmentID()).getDepartmentName();
            case 4:
                return p -> p.getManager() ? "Yes" : "No";
                default:
                    return super.cellValueForColumnAt(index);
        }
    }

    private void reloadDataFromServer(){

        if(!_doctorLoad){
            loadDoctors();
            return;
        }

        if(!_hospitalLoad){
            loadHospitals();
            return;
        }

        if(!_departmentLoad){
            loadDepartments();
            return;
        }


        doctorList.clear();
        doctorAPI.readAll((response, items) -> {
            if(AutoSignIn.ROLE_ID < 6) {

                if (AutoSignIn.ROLE_ID < 5){
                    new ProfileAPI().readAll((response1, items1) -> {
                        Person self = null;
                        for (Person person : items1) {
                            if (AutoSignIn.ID.equals(person.getACCOUNT_ID())) {
                                self = person;
                                break;
                            }
                        }

                        if(self == null)
                            return;

                        Doctor selfDoctor = null;
                        for (Doctor item : items) {
                            if (item.getDoctorID().equals(self.getID())) {
                                selfDoctor = item;
                                break;
                            }
                        }

                        if(selfDoctor == null)
                            return;

                        for (Doctor item : items) {
                            if (item.getHospitalID().equals(selfDoctor.getHospitalID()) &&
                                    item.getDepartmentID().equals(selfDoctor.getDepartmentID())) {
                                doctorList.add(item);
                            }
                        }
                        reloadData();
                    });
                }else {
                    for (Doctor item : items) {
                        if (AutoSignIn.HOSPITAL_ID.equals(item.getHospitalID())) {
                            doctorList.add(item);
                        }
                    }
                    reloadData();
                }


            }else {
                doctorList.addAll(items);
                reloadData();
            }
        });

    }

    private boolean _doctorLoad = false;
    private boolean _hospitalLoad = false;
    private boolean _departmentLoad = false;

    private void loadDoctors(){
        if(!_doctorLoad)
            new ProfileAPI().readAll((response, items) -> {
                names.clear();

                for (Person item : items)
                    names.put(item.getID(),item.getFirstName() + " "+ item.getSurName());

                _doctorLoad = true;
                reloadDataFromServer();
            });
    }

    private void loadHospitals(){
        if(!_hospitalLoad)
            new HospitalAPI().readAll((response, items) -> {
                hospitalMap.clear();

                for (Hospital item : items)
                    hospitalMap.put(item.getHospitalID(),item);

                _hospitalLoad = true;
                reloadDataFromServer();
            });
    }

    private void loadDepartments(){
        if(!_departmentLoad)
            new DepartmentAPI().readAll(new Department(0),(response, items) -> {
                departmentMap.clear();

                for (Department department : items) {
                    departmentMap.put(department.getHospitalID() + "_" + department.getDepartmentID(),department);
                }

                _departmentLoad = true;
                reloadDataFromServer();
            });
    }
}
