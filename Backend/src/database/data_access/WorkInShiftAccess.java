package database.data_access;

import database.GenericAccess;
import model.WorkInShift;

/**
 * Created By Tony on 19/07/2018
 */
public class WorkInShiftAccess extends GenericAccess<WorkInShift> {
    public WorkInShiftAccess() { super(WorkInShift.class,"worksInShift"); }
}
