package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.MedicalEventTypeInDepartment;

/**
 * Created By Tony on 19/07/2018
 */
public class MedicalEventTypeInDepartmentAccess extends GenericAccess<MedicalEventTypeInDepartment> {

    public MedicalEventTypeInDepartmentAccess() {
        super(MedicalEventTypeInDepartment.class, "MedicalEventTypeInDepartment");
    }

    public MedicalEventTypeInDepartmentAccess(Database db) { super(
            MedicalEventTypeInDepartment.class,
            "MedicalEventTypeInDepartment",
            db);
    }
}
