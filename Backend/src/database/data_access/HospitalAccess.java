package database.data_access;

import database.GenericAccess;
import model.Hospital;

/**
 * Created By Tony on 19/07/2018
 */
public class HospitalAccess extends GenericAccess<Hospital> {
    public HospitalAccess() { super(Hospital.class,"Hospital"); }
}
