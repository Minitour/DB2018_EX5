package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.CheckedBy;

/**
 * Created By Tony on 19/07/2018
 */
public class CheckedByAccess extends GenericAccess<CheckedBy> {

    public CheckedByAccess() { super(CheckedBy.class,"checkedBy"); }

    public CheckedByAccess(Database db) { super(CheckedBy.class,"checkedBy",db); }
}
