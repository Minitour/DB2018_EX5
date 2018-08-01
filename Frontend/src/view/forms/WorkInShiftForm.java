package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import model.Doctor;
import model.Person;
import model.Shift;
import model.WorkInShift;
import network.api.DoctorAPI;
import network.api.ProfileAPI;
import network.api.ShiftAPI;
import network.generic.GenericAPI;
import utils.Response;
import view.generic.UIFormView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class WorkInShiftForm extends UIFormView<WorkInShift> {

    private ObservableList<ComboItem> doctors;
    private ObservableList<ComboItem> shifts;

    public WorkInShiftForm(WorkInShift existingValue, OnFinish<WorkInShift> callback) {
        super(WorkInShift.class, existingValue, callback);
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
        return new String[]{"doctorID", "shiftNumber"};
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
            case "shiftNumber":
                return shifts;

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
        return new String[]{"hospitalID", "departmentID"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        doctors = FXCollections.observableArrayList();
        shifts = FXCollections.observableArrayList();

        // doctors
        new DoctorAPI().readAll((response, items) -> {
            if(response.isOK()){
                new ProfileAPI().readAll((response1, items1) -> {
                    Map<String,String> names = new HashMap<>();
                    for(Person p : items1)
                        names.put(p.getID(),p.getFirstName() +" " +p.getSurName());

                    for (Doctor item : items)
                        doctors.add(new ComboItem(names.get(item.getDoctorID()),item.getDoctorID()));
                });
            }


        });

        // shifts
        new ShiftAPI().readAll((response, items) -> {
            if(response.isOK())
                for (Shift item : items)
                    shifts.add(new ComboItem("#"+item.getShiftNumber()+", "+item.dayLiteralValue()+", "+item.typeLiteralValue(),String.valueOf(item.getShiftNumber())));

        });
    }
}
