package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.MedicalEvent;

/**
 * Created By Tony on 19/07/2018
 */
public class MedicalEventAccess extends GenericAccess<MedicalEvent> {
    public MedicalEventAccess() {
        super(MedicalEvent.class,"MedicalEvent");
    }
    public MedicalEventAccess(Database db) {
        super(MedicalEvent.class,"MedicalEvent",db);
    }
}
