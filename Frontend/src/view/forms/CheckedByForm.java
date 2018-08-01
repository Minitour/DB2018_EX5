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
import sun.java2d.cmm.Profile;
import utils.AutoColor;
import utils.AutoSignIn;
import utils.Response;
import view.generic.UIFormView;
import view.tables.HospitalizationTableView;

import java.util.*;

/**
 * Created By Tony on 26/07/2018
 */
public class CheckedByForm extends UIFormView<CheckedBy> {

    private ObservableList<ComboItem> patients;
    private ObservableList<ComboItem> events;
    private ObservableList<ComboItem> doctors;
    private ObservableList<ComboItem> shifts;

    //key: person id, value hospitalizations
    private Map<String,Set<Hospitalized>> hospitalizedMap;

    //key: doctor id, value: shift code
    private Map<String,Set<Integer>> shiftNumbers;

    private Map<Integer,MedicalEvent> eventMap;

    @Override
    protected DatePicker datePickerFactory() {
        JFXDatePicker picker = new JFXDatePicker();
        picker.setDefaultColor(Color.web(AutoColor.secondaryColor));
        return picker;
    }

    public CheckedByForm(CheckedBy existingValue, OnFinish<CheckedBy> callback) {
        super(CheckedBy.class, existingValue, callback);
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
        return new String[]{"patientID", "eventCode", "doctorID", "shiftNumber"};
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

        switch (fieldName) {
            case "patientID":
                return patients;
            case "eventCode":
                return events;
            case "doctorID":
                return doctors;
            case "shiftNumber":
                return shifts;
        }

        return null;
    }

    @Override
    protected void didComboSelectionChanged(String nameField, ComboItem value) {
        switch (nameField){
            case "patientID":
                ComboBox eventsBox = (ComboBox) elements_vbox.lookup("#eventCode");
                eventsBox.getSelectionModel().clearSelection();
                events.clear();
                Set<Hospitalized> d = hospitalizedMap.get(value.value);
                if(d == null)
                    return;
                for (Hospitalized hospitalized : d) {
                    int eventCode = hospitalized.getEventCode();
                    MedicalEvent event = eventMap.get(eventCode);
                    events.add(new ComboItem(event.getDescription(),event.getEventCode()));
                }
                break;
            case "doctorID":
                ComboBox shiftBox = (ComboBox) elements_vbox.lookup("#shiftNumber");
                shiftBox.getSelectionModel().clearSelection();
                new WorkInShiftAPI().readAll((response, items) -> {
                    shifts.clear();
                    if(response.isOK())
                        for (WorkInShift item : items)
                            if(item.getDoctorID().equals(value.value))
                                shifts.add(new ComboItem(
                                        String.valueOf(item.getShiftNumber())
                                ));

                });
                break;

        }
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
        return new String[]{"patientID", "eventCode", "doctorID", "shiftNumber","checkTime"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        patients = FXCollections.observableArrayList();
        events = FXCollections.observableArrayList();
        doctors = FXCollections.observableArrayList();
        shifts = FXCollections.observableArrayList();

        hospitalizedMap = new HashMap<>();
        shiftNumbers = new HashMap<>();
        eventMap = new HashMap<>();

        new EventAPI().readAll((response, items) -> {
            eventMap.clear();

            for (MedicalEvent item : items){
                events.add(new ComboItem(
                        item.getDescription(),
                        item.getEventCode()
                ));
                eventMap.put(item.getEventCode(),item);
            }
        });

        new WorkInShiftAPI().readAll((response, items) -> {
            shifts.clear();
            if(response.isOK())
                for (WorkInShift item : items)
                    shifts.add(new ComboItem(
                            String.valueOf(item.getShiftNumber())
                        ));

        });


        new ProfileAPI().readAll((response, items) -> {

            Map<String,String> names = new HashMap<>();
            if(response.isOK())
                for (Person item : items)
                    names.put(item.getID(),item.getFirstName() + " " + item.getSurName());

            new HospitalizedAPI().readAll(new Hospitalized(AutoSignIn.HOSPITAL_ID,0),(res1, hospitalizeds) -> {
                for (Hospitalized hospitalized : hospitalizeds) {
                    patients.add(new ComboItem(names.get(hospitalized.getPatientID()), hospitalized.getPatientID()));

                    if(hospitalizedMap.containsKey(hospitalized.getPatientID())){
                        //add to set
                        hospitalizedMap.get(hospitalized.getPatientID()).add(hospitalized);
                    }else{
                        Set<Hospitalized> nset = new HashSet<>();
                        nset.add(hospitalized);
                        hospitalizedMap.put(hospitalized.getPatientID(),nset);
                    }
                }
            });

            new DoctorAPI().readAll((res1, doctors) -> {
                if(response.isOK()) {
                    for (Doctor item : doctors) {
                        this.doctors.add(new ComboItem(names.get(item.getDoctorID()),item.getDoctorID()));
                    }

                }
            });

        });



    }
}
