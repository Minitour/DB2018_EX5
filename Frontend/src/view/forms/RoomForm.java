package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Department;
import model.Hospital;
import model.Room;
import network.api.DepartmentAPI;
import network.api.HospitalAPI;
import network.api.RoomAPI;
import utils.AutoSignIn;
import view.generic.UIFormView;

import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class RoomForm extends UIFormView<Room> {

    private ObservableList<ComboItem> hospitalList;
    private ObservableList<ComboItem> departmentList;

    public RoomForm(Room existingValue, OnFinish<Room> callback) {
        super(Room.class, existingValue, callback);
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
        return new String[]{"hospitalID", "departmentID"};
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
            case "hospitalID":
                return hospitalList;
            case "departmentID":
                return departmentList;

        }
        return null;
    }

    @Override
    protected void didComboSelectionChanged(String nameField, ComboItem value) {
        switch (nameField){
            case "hospitalID":
                if(value != null) {
                    int hospitalId = (int) value.value;
                    ComboBox comboBox = (ComboBox) elements_vbox.lookup("#departmentID");
                    comboBox.getSelectionModel().clearSelection();
                    this.departmentList.clear();
                    new DepartmentAPI().readAll(new Department(hospitalId),(response, items) -> {
                        if(response.isOK())
                            for (Department item : items)
                                departmentList.add(new ComboItem(
                                        item.getDepartmentName(),
                                        item.getDepartmentID()
                                ));

                    });
                }
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
        return new String[]{"hospitalID", "departmentID", "roomNumber"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        hospitalList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        new HospitalAPI().readAll((response, items) -> {
            if(response.isOK())
                for (Hospital item : items)
                    // if super user  - all
                    // else can enter only with the same hospitalID as
                    if (AutoSignIn.ROLE_ID == 6 || item.getHospitalID().equals(AutoSignIn.HOSPITAL_ID))
                        hospitalList.add(new ComboItem(
                                item.getName(),
                                item.getHospitalID()
                        ));

        });

//        // departments
//        new DepartmentAPI().readAll(new Department(AutoSignIn.HOSPITAL_ID),(response, items) -> {
//            if(response.isOK())
//                for (Department item : items)
//                    departmentList.add(new ComboItem(
//                            item.getDepartmentName(),
//                            item.getDepartmentID()
//                    ));
//
//        });
    }
}
