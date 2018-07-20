package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.WorkInShift;

/**
 * Created By Tony on 19/07/2018
 */
public class WorkInShiftAccess extends GenericAccess<WorkInShift> {
    public WorkInShiftAccess() { super(WorkInShift.class,"worksInShift"); }
    public WorkInShiftAccess(Database db) { super(WorkInShift.class,"worksInShift",db); }
}
