package view.forms;

import model.DoctorVacation;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class DoctorVacationForm extends UIFormView<DoctorVacation> {
    public DoctorVacationForm(DoctorVacation existingValue, OnFinish<DoctorVacation> callback) {
        super(DoctorVacation.class, existingValue, callback);
    }
}
