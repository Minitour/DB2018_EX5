package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import model.Account;
import model.Hospital;
import network.api.HospitalAPI;
import view.generic.UIFormView;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class AccountForm extends UIFormView<Account> {

    private ObservableList<ComboItem> hospitalList;

    public AccountForm(Account existingValue, OnFinish<Account> callback) { super(Account.class, existingValue, callback); }

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
        return new String[]{"hospitalID","ROLE_ID"};
    }

    @Override
    protected String[] protectedFields() {
        return new String[]{"USER_PASSWORD"};
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
            case "ROLE_ID":
                return FXCollections.observableArrayList(Arrays.asList(
                        new ComboItem("Patient",1),
                        new ComboItem("Secretary",2),
                        new ComboItem("Doctor",3),
                        new ComboItem("Doctor Manager",4),
                        new ComboItem("Admin",5),
                        new ComboItem("Super User",6)
                ));

        }
        return null;
    }

    /**
     * avoid insertion for account id
     * @return
     */
    @Override
    public String[] defaultValueFields() {
        return new String[]{"ACCOUNT_ID"};
    }

    /**
     * supply default value on insert
     *
     * @param fieldName
     * @return
     */
    @Override
    protected Object defaultValueForField(String fieldName) {
        switch (fieldName){
            case "ACCOUNT_ID": return 0;
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
        return new String[]{"ACCOUNT_ID","EMAIL","USER_PASSWORD"};
    }

    @Override
    protected String[] hiddenFields() {
        return new String[]{"USER_PASSWORD"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        hospitalList = FXCollections.observableArrayList();

        new HospitalAPI().readAll((response, items) -> {
            if(response.isOK())
                for (Hospital item : items)
                    hospitalList.add(new ComboItem(
                            item.getName(),
                            item.getHospitalID()
                    ));

        });
    }
}
