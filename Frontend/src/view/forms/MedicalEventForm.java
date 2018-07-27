package view.forms;

import model.MedicalEvent;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class MedicalEventForm extends UIFormView<MedicalEvent> {
    public MedicalEventForm(MedicalEvent existingValue, OnFinish<MedicalEvent> callback) {
        super(MedicalEvent.class, existingValue, callback);
    }
}
