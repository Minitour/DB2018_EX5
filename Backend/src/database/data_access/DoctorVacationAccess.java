package database.data_access;

import database.GenericAccess;
import model.DoctorVacation;

/**
 * Created By Tony on 19/07/2018
 */
public class DoctorVacationAccess extends GenericAccess<DoctorVacation> {
    public DoctorVacationAccess() { super(DoctorVacation.class,"DoctorVacations"); }
}
