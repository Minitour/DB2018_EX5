package view.tables;

import model.Hospital;
import network.api.HospitalAPI;
import view.forms.HospitalForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class HospitalTableView extends GenericTableView<Hospital> {

    private HospitalAPI api ;
    private List<Hospital> hospitals = new ArrayList<>();

    public HospitalTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Hospital hospital = hospitals.remove(index);
        api.delete(hospital,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Hospital> onView(int index) {
        Hospital hospital = hospitals.get(index);
        return new HospitalForm(hospital,this);
    }

    @Override
    protected UIFormView<Hospital> onInsert() {
        return new HospitalForm(null, this);
    }

    @Override
    protected Class<Hospital> classType() {
        return Hospital.class;
    }

    @Override
    public Collection<? extends Hospital> dataSource() {
        return hospitals;
    }

    @Override
    public void callback(Hospital value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> reloadDataFromServer());
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new HospitalAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            hospitals.clear();
            hospitals.addAll(items);
            reloadData();
        });
    }
}
