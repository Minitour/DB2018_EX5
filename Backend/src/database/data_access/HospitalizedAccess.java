package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Hospitalized;

/**
 * Created By Tony on 19/07/2018
 */
public class HospitalizedAccess extends GenericAccess<Hospitalized> {
    public HospitalizedAccess() { super(Hospitalized.class,"Hospitalized"); }
    public HospitalizedAccess(Database db) { super(Hospitalized.class,"Hospitalized",db); }
}
