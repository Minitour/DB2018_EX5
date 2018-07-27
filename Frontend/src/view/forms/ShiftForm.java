package view.forms;

import model.Shift;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class ShiftForm extends UIFormView<Shift> {
    public ShiftForm(Shift existingValue, OnFinish<Shift> callback) {
        super(Shift.class, existingValue, callback);
    }
}
