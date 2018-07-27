package view.forms;

import model.MedicalEventTypeInDepartment;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class MedicalEventTypeInDepartmentForm extends UIFormView<MedicalEventTypeInDepartment> {
    public MedicalEventTypeInDepartmentForm(MedicalEventTypeInDepartment existingValue, OnFinish<MedicalEventTypeInDepartment> callback) {
        super(MedicalEventTypeInDepartment.class, existingValue, callback);
    }
}
