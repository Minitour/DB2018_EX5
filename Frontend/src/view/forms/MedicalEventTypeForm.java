package view.forms;

import model.MedicalEventTypes;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class MedicalEventTypeForm extends UIFormView<MedicalEventTypes> {
    public MedicalEventTypeForm(MedicalEventTypes existingValue, OnFinish<MedicalEventTypes> callback) {
        super(MedicalEventTypes.class, existingValue, callback);
    }
}
