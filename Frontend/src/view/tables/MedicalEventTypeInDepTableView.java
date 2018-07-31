package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.*;
import network.api.*;
import ui.UITableView;
import utils.AutoSignIn;
import view.forms.MedicalEventTypeInDepartmentForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

public class MedicalEventTypeInDepTableView extends GenericTableView<MedicalEventTypeInDepartment> {

    private EventInDepartmentAPI api ;
    private List<MedicalEventTypeInDepartment> events = new ArrayList<>();

    private Map<Integer, MedicalEventTypes> medicalEventMap;
    private Map<Integer, Hospital> hospitalMap;
    private Map<String, Department> departmentMap;

    public MedicalEventTypeInDepTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        MedicalEventTypeInDepartment medicalEvent = events.remove(index);
        api.delete(medicalEvent,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<MedicalEventTypeInDepartment> onView(int index) {
        MedicalEventTypeInDepartment medicalEvent = events.get(index);
        return new MedicalEventTypeInDepartmentForm(medicalEvent,this);
    }

    @Override
    protected UIFormView<MedicalEventTypeInDepartment> onInsert() {
        return new MedicalEventTypeInDepartmentForm(null, this);
    }

    @Override
    protected Class<MedicalEventTypeInDepartment> classType() {
        return MedicalEventTypeInDepartment.class;
    }

    @Override
    public Collection<? extends MedicalEventTypeInDepartment> dataSource() {
        return events;
    }

    @Override
    public void callback(MedicalEventTypeInDepartment value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public TableColumnValue<MedicalEventTypeInDepartment> cellValueForColumnAt(int index) {
        switch (index){
            case 0: return metid -> hospitalMap.get(metid.getHospitalID()).getName();
            case 1: return metid -> departmentMap.get(metid.getHospitalID() + "_" +metid.getDepartmentID()).getDepartmentName();
            case 2: return metid -> medicalEventMap.get(metid.getTypeCode()).getTypeName();
            default: return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        medicalEventMap = new HashMap<>();
        hospitalMap = new HashMap<>();
        departmentMap = new HashMap<>();

        api = new EventInDepartmentAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        if(!_eventsLoad){
            loadEvents();
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

        api.readAll(new MedicalEventTypeInDepartment(AutoSignIn.HOSPITAL_ID),(response, items) -> {
            if(response.isOK()) {
                events.clear();
                events.addAll(items);
                reloadData();
            }
        });
    }

    private boolean _eventsLoad = false;
    private boolean _hospitalLoad = false;
    private boolean _departmentLoad = false;

    private void loadEvents(){
        if(!_eventsLoad)
            new EventTypesAPI().readAll((response, items) -> {
                medicalEventMap.clear();

                for (MedicalEventTypes item : items)
                    medicalEventMap.put(item.getTypeCode(),item);

                _eventsLoad = true;
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
            new DepartmentAPI().readAll(new Department(AutoSignIn.HOSPITAL_ID),(response, items) -> {
                departmentMap.clear();

                for (Department department : items) {
                    departmentMap.put(department.getHospitalID() + "_" + department.getDepartmentID(),department);
                }

                _departmentLoad = true;
                reloadDataFromServer();
            });
    }
}
