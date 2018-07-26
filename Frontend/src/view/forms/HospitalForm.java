package view.forms;

import model.Hospital;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalForm extends UIFormView<Hospital> {
    public HospitalForm(Hospital existingValue, OnFinish<Hospital> callback) {
        super(Hospital.class, existingValue, callback);
    }
}
