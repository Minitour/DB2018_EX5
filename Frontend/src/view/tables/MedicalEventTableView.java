package view.tables;

import model.MedicalEvent;
import network.api.EventAPI;
import view.forms.MedicalEventForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class MedicalEventTableView extends GenericTableView<MedicalEvent> {

    private EventAPI api ;
    private List<MedicalEvent> events = new ArrayList<>();

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
        api.upsert(value, response -> reloadDataFromServer());
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
            events.clear();
            events.addAll(items);
            reloadData();
        });
    }
}
