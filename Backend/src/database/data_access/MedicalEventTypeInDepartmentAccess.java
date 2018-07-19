package database.data_access;

import database.GenericAccess;
import model.MedicalEventTypeInDepartment;

/**
 * Created By Tony on 19/07/2018
 */
public class MedicalEventTypeInDepartmentAccess extends GenericAccess<MedicalEventTypeInDepartment> {
    public MedicalEventTypeInDepartmentAccess() {
        super(MedicalEventTypeInDepartment.class, "MedicalEventTypeInDepartment");
    }
}
