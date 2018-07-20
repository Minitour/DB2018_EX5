package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Doctor;

/**
 * Created By Tony on 19/07/2018
 */
public class DoctorAccess extends GenericAccess<Doctor> {

    public DoctorAccess() { super(Doctor.class,"Doctor"); }

    public DoctorAccess(Database db) { super(Doctor.class,"Doctor",db); }
}
