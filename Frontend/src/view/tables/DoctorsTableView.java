package view.tables;

import model.Doctor;
import network.api.DoctorAPI;
import view.forms.DoctorForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorsTableView extends GenericTableView<Doctor> {

    private DoctorAPI doctorAPI;
    private List<Doctor> doctorList = new ArrayList<>();


    public DoctorsTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Doctor doctor = doctorList.remove(index);
        doctorAPI.delete(doctor,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Doctor> onView(int index) {
        Doctor doctor = doctorList.get(index);
        return new DoctorForm(doctor,this);
    }

    @Override
    protected UIFormView<Doctor> onInsert() {
        return new DoctorForm(null, this);
    }

    @Override
    protected Class<Doctor> classType() {
        return Doctor.class;
    }

    @Override
    public Collection<? extends Doctor> dataSource() {
        return doctorList;
    }

    private void reloadDataFromServer(){
        doctorAPI.readAll((response, items) -> {
            doctorList.clear();
            doctorList.addAll(items);
            reloadData();
        });
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        doctorAPI = new DoctorAPI();

        doctorAPI.setRunOnUi(true);
        reloadDataFromServer();
    }

    @Override
    public void callback(Doctor value) {
        super.callback(value);
        //on update or insert
        doctorAPI.upsert(value, response -> reloadDataFromServer());
    }
}
