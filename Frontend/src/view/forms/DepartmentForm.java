package view.forms;

import model.Department;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class DepartmentForm extends UIFormView<Department> {
    public DepartmentForm(Department existingValue, OnFinish<Department> callback) {
        super(Department.class, existingValue, callback);
    }
}
