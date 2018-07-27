package view.tables;

import model.MedicalEventTypeInDepartment;
import network.api.EventInDepartmentAPI;
import view.forms.MedicalEventTypeInDepartmentForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class MedicalEventTypeInDepTableView extends GenericTableView<MedicalEventTypeInDepartment> {

    private EventInDepartmentAPI api ;
    private List<MedicalEventTypeInDepartment> events = new ArrayList<>();

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
        api.upsert(value, response -> reloadDataFromServer());
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new EventInDepartmentAPI();
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
