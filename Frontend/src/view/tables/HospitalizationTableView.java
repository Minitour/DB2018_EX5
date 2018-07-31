package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.*;
import network.api.DepartmentAPI;
import network.api.EventAPI;
import network.api.HospitalAPI;
import network.api.HospitalizedAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.AutoSignIn;
import utils.Response;
import view.forms.HospitalizedForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import javax.crypto.spec.DESedeKeySpec;
import java.util.*;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalizationTableView extends GenericTableView<Hospitalized> {

    private List<Hospitalized> list = new ArrayList<>();

    private Map<Integer, MedicalEvent> medicalEventMap;
    private Map<Integer, Hospital> hospitalMap;
    private Map<String, Department> departmentMap;

    private HospitalizedAPI api;

    public HospitalizationTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        api.delete(list.remove(index),response -> reloadDataFromServer());
        reloadData();
    }

    @Override
    protected UIFormView<Hospitalized> onView(int index) {
        return new HospitalizedForm(list.get(index),this);
    }

    @Override
    protected UIFormView<Hospitalized> onInsert() {
        return new HospitalizedForm(null,this);
    }

    @Override
    protected Class<Hospitalized> classType() {
        return Hospitalized.class;
    }

    @Override
    public Collection<? extends Hospitalized> dataSource() {
        return list;
    }

    @Override
    public void callback(Hospitalized value) {
        super.callback(value);
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        medicalEventMap = new HashMap<>();
        hospitalMap = new HashMap<>();
        departmentMap = new HashMap<>();

        api = new HospitalizedAPI();
        api.setRunOnUi(true);
        loadEvents();
        loadHospitals();
        loadDepartments();

        reloadDataFromServer();
    }

    @Override
    public TableColumnValue<Hospitalized> cellValueForColumnAt(int index) {
        switch (index){
            case 1:
                return object -> medicalEventMap.get(object.getEventCode()).getDescription();
            case 5:
                return object -> hospitalMap.get(object.getHospitalID()).getName();
            case 6:
                return object -> departmentMap.get(object.getHospitalID() + "_" + object.getDepartmentID()).getDepartmentName();
                default:
                    return super.cellValueForColumnAt(index);
        }
    }

    private boolean _eventsLoad = false;
    private boolean _hospitalLoad = false;
    private boolean _departmentLoad = false;

    private void loadEvents(){
        if(!_eventsLoad)
            new EventAPI().readAll((response, items) -> {
                medicalEventMap.clear();

                for (MedicalEvent item : items)
                    medicalEventMap.put(item.getEventCode(),item);

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

        Hospitalized h = new Hospitalized(AutoSignIn.HOSPITAL_ID,0);
        api.readAll(h, (response, items) -> {
            list.clear();
            list.addAll(items);
            reloadData();
        });
    }
}
