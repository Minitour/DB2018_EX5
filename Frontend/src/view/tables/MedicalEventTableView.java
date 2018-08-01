package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.MedicalEvent;
import model.MedicalEventTypes;
import network.api.EventAPI;
import network.api.EventTypesAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.Response;
import view.forms.MedicalEventForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

public class MedicalEventTableView extends GenericTableView<MedicalEvent> {

    private EventAPI api ;
    private List<MedicalEvent> events = new ArrayList<>();
    private Map<Integer,String> eventCodeNames = new HashMap<>();

    public MedicalEventTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        MedicalEvent medicalEvent = events.remove(index);
        api.delete(medicalEvent,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<MedicalEvent> onView(int index) {
        MedicalEvent medicalEvent = events.get(index);
        return new MedicalEventForm(medicalEvent,this);
    }

    @Override
    protected UIFormView<MedicalEvent> onInsert() {
        return new MedicalEventForm(null, this);
    }

    @Override
    protected Class<MedicalEvent> classType() {
        return MedicalEvent.class;
    }

    @Override
    public Collection<? extends MedicalEvent> dataSource() {
        return events;
    }

    @Override
    public void callback(MedicalEvent value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public TableColumnValue<MedicalEvent> cellValueForColumnAt(int index) {
        switch (index){
            case 2:
                return event -> eventCodeNames.get(event.getTypeCode());
                default:return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new EventAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            if(response.isOK()){
                new EventTypesAPI().readAll((response1, items1) -> {
                    for(MedicalEventTypes e : items1){
                        eventCodeNames.put(e.getTypeCode(),e.getTypeName());
                    }

                    events.clear();
                    events.addAll(items);
                    reloadData();
                });
            }
        });
    }
}
