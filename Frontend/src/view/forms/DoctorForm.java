package view.forms;

import model.Doctor;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class DoctorForm extends UIFormView<Doctor> {
    public DoctorForm(Doctor existingValue, OnFinish<Doctor> callback) {
        super(Doctor.class, existingValue, callback);
    }
}
