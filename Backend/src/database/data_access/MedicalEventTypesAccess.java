package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.MedicalEventTypes;

/**
 * Created By Tony on 19/07/2018
 */
public class MedicalEventTypesAccess extends GenericAccess<MedicalEventTypes> {
    public MedicalEventTypesAccess() {
        super(MedicalEventTypes.class,"MedicalEventTypes");
    }

    public MedicalEventTypesAccess(Database db) {
        super(MedicalEventTypes.class,"MedicalEventTypes",db);
    }
}
