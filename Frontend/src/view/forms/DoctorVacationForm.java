package view.forms;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Doctor;
import model.DoctorVacation;
import model.Person;
import network.api.DoctorAPI;
import network.api.ProfileAPI;
import network.generic.GenericAPI;
import utils.AutoColor;
import utils.Response;
import view.generic.UIFormView;

import java.util.*;

/**
 * Created By Tony on 26/07/2018
 */
public class DoctorVacationForm extends UIFormView<DoctorVacation> {

    private ObservableList<ComboItem> doctors;


    public DoctorVacationForm(DoctorVacation existingValue, OnFinish<DoctorVacation> callback) {
        super(DoctorVacation.class, existingValue, callback);
    }

    @Override
    protected DatePicker datePickerFactory() {
        JFXDatePicker picker = new JFXDatePicker();
        picker.setDefaultColor(Color.web(AutoColor.secondaryColor));
        return picker;
    }

    @Override
    protected TextField textFieldFactory() {
        return new JFXTextField();
    }


    /**
     * This method is called once and is used to declare which fields are of type combo box.
     *
     * @return an array of field names that you wish to display as combo box.
     */
    @Override
    protected String[] comboBoxForFields() {
        return new String[]{"doctorID"};
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
            case "doctorID":
                return doctors;
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
        return new String[]{"doctorID", "vacationDate"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        doctors = FXCollections.observableArrayList();
        Map<String,String> names = new HashMap<>();


        new DoctorAPI().readAll((response, items) -> {
            if(response.isOK()){
                new ProfileAPI().readAll((response1, items1) -> {
                    for(Person p : items1){
                        names.put(p.getID(),p.getFirstName() + " " + p.getSurName());
                    }

                    for (Doctor item : items)
                        doctors.add(new ComboItem(
                                names.get(item.getDoctorID()),
                                item.getDoctorID()
                        ));
                });


            }
        });
    }
}
