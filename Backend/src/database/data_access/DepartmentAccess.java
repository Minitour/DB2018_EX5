package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Department;

/**
 * Created By Tony on 19/07/2018
 */
public class DepartmentAccess extends GenericAccess<Department> {

    public DepartmentAccess() { super(Department.class,"Department"); }

    public DepartmentAccess(Database db) { super(Department.class,"Department",db); }

}
