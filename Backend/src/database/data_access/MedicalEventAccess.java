package database.data_access;

import database.GenericAccess;
import model.MedicalEvent;

/**
 * Created By Tony on 19/07/2018
 */
public class MedicalEventAccess extends GenericAccess<MedicalEvent> {
    public MedicalEventAccess() {
        super(MedicalEvent.class,"MedicalEvent");
    }
}
