package view.forms;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.*;
import network.api.*;
import network.generic.GenericAPI;
import utils.AutoColor;
import utils.AutoSignIn;
import utils.Response;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class HospitalizedForm extends UIFormView<Hospitalized> {

    private ObservableList<ComboItem> hospitalList;
    private ObservableList<ComboItem> departmentList;
    private ObservableList<ComboItem> patients;
    private ObservableList<ComboItem> events;
    private ObservableList<ComboItem> rooms;

    public HospitalizedForm(Hospitalized existingValue, OnFinish<Hospitalized> callback) {
        super(Hospitalized.class, existingValue, callback);
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
        return new String[]{"patientID", "eventCode", "hospitalID", "departmentID", "roomNumber", "severityLevel"};
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
            case "patientID":
                return patients;
            case "hospitalID":
                return hospitalList;
            case "departmentID":
                return departmentList;
            case "eventCode":
                return events;
            case "roomNumber":
                return rooms;
            case "severityLevel":
                return FXCollections.observableArrayList(Arrays.asList(
                        new ComboItem("1",1),
                        new ComboItem("2",2),
                        new ComboItem("3",3),
                        new ComboItem("4",4),
                        new ComboItem("5",5),
                        new ComboItem("6",6),
                        new ComboItem("7",7),
                        new ComboItem("8",8),
                        new ComboItem("9",9),
                        new ComboItem("10",10)
                ));
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
        return new String[]{"patientID", "eventCode"};
    }

    @Override
    protected void didComboSelectionChanged(String nameField, ComboItem value) {
        switch (nameField){
            case "departmentID":
                if(value != null) {
                    int departmentId = (int) value.value;
                    ComboBox comboBox = (ComboBox) elements_vbox.lookup("#roomNumber");
                    comboBox.getSelectionModel().clearSelection();
                    this.rooms.clear();
                    new RoomAPI().readAll(new Room(AutoSignIn.HOSPITAL_ID, departmentId), (response, items) -> {
                        if(response.isOK()){
                            for (Room item : items) {
                                rooms.add(new ComboItem(
                                        String.valueOf(item.getRoomNumber())
                                ));
                            }
                        }
                    });
                }
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        hospitalList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();
        patients = FXCollections.observableArrayList();
        events = FXCollections.observableArrayList();
        rooms = FXCollections.observableArrayList();

        // patients
        new PatientsAPI().readAll((response, items) -> {
            if(response.isOK())
                for (Person item : items)
                    patients.add(new ComboItem(
                            item.getFirstName() + ", " + item.getSurName(),
                            item.getID()
                    ));

        });

        // event codes
        new EventAPI().readAll((response, items) -> {
            if(response.isOK())
                for (MedicalEvent item : items)
                    events.add(new ComboItem(
                            item.getDescription(),
                            item.getEventCode()
                    ));

        });

        // hospitals
        new HospitalAPI().readAll((response, items) -> {
            if(response.isOK())
                for (Hospital item : items)
                    if(item.getHospitalID() == AutoSignIn.HOSPITAL_ID)
                        hospitalList.add(new ComboItem(
                                item.getName(),
                                item.getHospitalID()
                        ));

        });

        // departments
        new DepartmentAPI().readAll(new Department(AutoSignIn.HOSPITAL_ID),(response, items) -> {
            if(response.isOK())
                for (Department item : items)
                    departmentList.add(new ComboItem(
                            item.getDepartmentName(),
                            item.getDepartmentID()
                    ));

        });

//        // rooms
//        new RoomAPI().readAll((response, items) -> {
//            if(response.isOK())
//                for (Room item : items)
//                    rooms.add(new ComboItem(
//                            String.valueOf(item.getRoomNumber())
//                    ));
//
//        });
    }
}
