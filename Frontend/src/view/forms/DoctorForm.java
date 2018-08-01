package view.forms;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Doctor;
import utils.AutoColor;
import view.generic.UIFormView;

import java.util.Arrays;

/**
 * Created By Tony on 26/07/2018
 */
public class DoctorForm extends UIFormView<Doctor> {
    public DoctorForm(Doctor existingValue, OnFinish<Doctor> callback) {
        super(Doctor.class, existingValue, callback);
    }


    @Override
    protected TextField textFieldFactory() {
        return new JFXTextField();
    }


    @Override
    protected DatePicker datePickerFactory() {
        JFXDatePicker picker = new JFXDatePicker();
        picker.setDefaultColor(Color.web(AutoColor.secondaryColor));
        return picker;
    }

    /**
     * This method is called once and is used to declare which fields are of type combo box.
     *
     * @return an array of field names that you wish to display as combo box.
     */
    @Override
    protected String[] comboBoxForFields() {
        return new String[]{"manager"};
    }

    /**
     * Here we return an observable list.
     * Note that this observable list is retained within the form itself inside a hashmap.
     *
     * If you wish to return a list that is pulled from a server, then create a reference as a field member of this class.
     * Then make the api call in the layoutSubviews method and in the completion closure add all the items to the observable list.
     *
     * Since this is an observable list we don't have to reload it and so this method is only called once.
     *
     * @param fieldName The name of the field which is of type combobox and we wish to display as set of items for it.
     * @return The observable list for the combo box containing the items we want to display.
     */
    @Override
    protected ObservableList<ComboItem> listForField(String fieldName) {
        switch (fieldName){
            case "manager":
                return FXCollections.observableArrayList(Arrays.asList(
                        new ComboItem("Yes",1),
                        new ComboItem("No",0))
                );
        }
        return null;
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
        return new String[]{"doctorID"};
    }
}
