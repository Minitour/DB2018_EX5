package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.Hospitalized;
import network.api.HospitalizedAPI;
import utils.AutoSignIn;
import view.forms.HospitalizedForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalizationTableView extends GenericTableView<Hospitalized> {

    private List<Hospitalized> list = new ArrayList<>();
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
        api = new HospitalizedAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }


    private void reloadDataFromServer(){
        Hospitalized h = new Hospitalized(AutoSignIn.HOSPITAL_ID,0);
        api.readAll(h, (response, items) -> {
            list.clear();
            list.addAll(items);
            reloadData();
        });
    }
}
