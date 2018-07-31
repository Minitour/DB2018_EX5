package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.CheckedBy;
import model.MedicalEvent;
import model.Person;
import network.api.CheckedByAPI;
import network.api.EventAPI;
import network.api.ProfileAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.Response;
import view.forms.CheckedByForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

/**
 * Created By Tony on 27/07/2018
 */
public class ChecksTableView extends GenericTableView<CheckedBy> {

    private CheckedByAPI api;
    private List<CheckedBy> list = new ArrayList<>();
    private Map<String,String> names;
    private Map<Integer, MedicalEvent> eventMap;

    public ChecksTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        api.delete(list.remove(index),response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<CheckedBy> onView(int index) {
        return new CheckedByForm(list.get(index),this);
    }

    @Override
    protected UIFormView<CheckedBy> onInsert() {
        return new CheckedByForm(null,this);
    }

    @Override
    protected Class<CheckedBy> classType() {
        return CheckedBy.class;
    }

    @Override
    public Collection<? extends CheckedBy> dataSource() {
        return list;
    }

    @Override
    public TableColumnValue<CheckedBy> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return p -> names.get(p.getPatientID());
            case 1:
                return p -> eventMap.get(p.getEventCode()).getDescription();
            case 2:
                return p -> names.get(p.getDoctorID());
                default:
                    return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        eventMap = new HashMap<>();
        names = new HashMap<>();

        api = new CheckedByAPI();
        reloadDataFromServer();
    }


    private boolean _namesLoad = false;
    private boolean _eventsLoad = false;

    private void loadEvents(){
        if(!_eventsLoad)
            new EventAPI().readAll((response, items) -> {
                eventMap.clear();

                for (MedicalEvent item : items)
                    eventMap.put(item.getEventCode(),item);

                _eventsLoad = true;
                reloadDataFromServer();
            });
    }

    private void loadNames(){
        if(!_namesLoad)
            new ProfileAPI().readAll(new Person(), (response, items) -> {
                for (Person item : items) {
                    names.put(item.getID(),item.getFirstName() + " " + item.getSurName());
                }

                _namesLoad = true;
                reloadDataFromServer();
            });

    }

    private void reloadDataFromServer(){
        if(!_namesLoad){
            loadNames();
            return;
        }

        if(!_eventsLoad){
            loadEvents();
            return;
        }

        api.readAll((response, items) -> {
            list.clear();
            list.addAll(items);
            reloadData();
        });
    }

    @Override
    public void callback(CheckedBy value) {
        super.callback(value);
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }
}
