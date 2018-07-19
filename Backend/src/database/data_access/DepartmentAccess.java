package database.data_access;

import database.GenericAccess;
import model.CheckedBy;
import model.Department;

/**
 * Created By Tony on 19/07/2018
 */
public class DepartmentAccess extends GenericAccess<Department> {
    public DepartmentAccess() { super(Department.class,"Department"); }
}
