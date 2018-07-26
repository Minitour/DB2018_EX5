package view.forms;

import model.WorkInShift;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class WorkInShiftForm extends UIFormView<WorkInShift> {
    public WorkInShiftForm(WorkInShift existingValue, OnFinish<WorkInShift> callback) {
        super(WorkInShift.class, existingValue, callback);
    }
}
