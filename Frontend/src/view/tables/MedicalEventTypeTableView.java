package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.MedicalEventTypes;
import network.api.EventTypesAPI;
import view.forms.MedicalEventTypeForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class MedicalEventTypeTableView extends GenericTableView<MedicalEventTypes> {

    EventTypesAPI api ;
    List<MedicalEventTypes> types = new ArrayList<>();

    public MedicalEventTypeTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        MedicalEventTypes vacation = types.remove(index);
        api.delete(vacation,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<MedicalEventTypes> onView(int index) {
        MedicalEventTypes vacation = types.get(index);
        return new MedicalEventTypeForm(vacation,this);
    }

    @Override
    protected UIFormView<MedicalEventTypes> onInsert() {
        return new MedicalEventTypeForm(null, this);
    }

    @Override
    protected Class<MedicalEventTypes> classType() {
        return MedicalEventTypes.class;
    }


    @Override
    public Collection<? extends MedicalEventTypes> dataSource() {
        return types;
    }

    @Override
    public void callback(MedicalEventTypes value) {
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
        api = new EventTypesAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            types.clear();
            types.addAll(items);
            reloadData();
        });
    }
}
