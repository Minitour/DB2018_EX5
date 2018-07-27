package view.forms;

import model.Hospital;
import model.Hospitalized;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalizedForm extends UIFormView<Hospitalized> {
    public HospitalizedForm(Hospitalized existingValue, OnFinish<Hospitalized> callback) {
        super(Hospitalized.class, existingValue, callback);
    }
}
