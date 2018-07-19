package database.data_access;

import database.GenericAccess;
import model.Shift;

/**
 * Created By Tony on 19/07/2018
 */
public class ShiftAccess extends GenericAccess<Shift> {
    public ShiftAccess() { super(Shift.class, "Shifts"); }
}
