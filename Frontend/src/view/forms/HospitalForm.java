package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import model.Hospital;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalForm extends UIFormView<Hospital> {

    public HospitalForm(Hospital existingValue, OnFinish<Hospital> callback) {
        super(Hospital.class, existingValue, callback);
    }

    @Override
    protected TextField textFieldFactory() {
        return new JFXTextField();
    }


    /**
     * This method is used to lock certain fields when viewing an existing object.
     * For example when viewing a person we do not wish for the user to edit the ID field.
     * In this case we return an array that contains "ID".
     *
     * @return A list of field names that will not be updated when in editing mode.
     */
    @Override
    public String[] inupdateableFields() {
        return new String[]{"hospitalID"};
    }
}
