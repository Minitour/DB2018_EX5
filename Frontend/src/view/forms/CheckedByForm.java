package view.forms;

import model.CheckedBy;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class CheckedByForm extends UIFormView<CheckedBy> {
    public CheckedByForm(CheckedBy existingValue, OnFinish<CheckedBy> callback) {
        super(CheckedBy.class, existingValue, callback);
    }
}
