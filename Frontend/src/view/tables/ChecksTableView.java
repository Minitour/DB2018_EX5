package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.CheckedBy;
import network.api.CheckedByAPI;
import view.forms.CheckedByForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 27/07/2018
 */
public class ChecksTableView extends GenericTableView<CheckedBy> {

    private CheckedByAPI api;
    private List<CheckedBy> list = new ArrayList<>();

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
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new CheckedByAPI();
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
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
