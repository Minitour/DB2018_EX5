package view.tables;

import model.Department;
import model.Hospital;
import network.api.DepartmentAPI;
import network.api.HospitalAPI;
import network.generic.GenericAPI;
import view.forms.DepartmentForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentTableView extends GenericTableView<Department> {

    private DepartmentAPI api ;
    private List<Department> departments = new ArrayList<>();

    public DepartmentTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Department department = departments.remove(index);
        api.delete(department,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Department> onView(int index) {
        Department department = departments.get(index);
        return new DepartmentForm(department,this);
    }

    @Override
    protected UIFormView<Department> onInsert() {
        return new DepartmentForm(null, this);
    }

    @Override
    protected Class<Department> classType() {
        return Department.class;
    }

    @Override
    public Collection<? extends Department> dataSource() {
        return departments;
    }

    @Override
    public void callback(Department value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> reloadDataFromServer());
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new DepartmentAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            departments.clear();
            departments.addAll(items);
            reloadData();
        });
    }
}
