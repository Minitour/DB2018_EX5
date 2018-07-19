package database.data_access;

import database.GenericAccess;
import model.CheckedBy;

/**
 * Created By Tony on 19/07/2018
 */
public class CheckedByAccess extends GenericAccess<CheckedBy> {
    public CheckedByAccess() { super(CheckedBy.class,"checkedBy"); }
}
