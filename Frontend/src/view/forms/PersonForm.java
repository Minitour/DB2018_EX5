package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import model.Person;
import view.generic.UIFormView;

import java.sql.Date;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class PersonForm extends UIFormView<Person> {


    private ObservableList<String> careFacilities;
    public PersonForm(Person p,OnFinish<Person> callback) {
        super(Person.class,p,callback);
    }

    @Override
    protected TextField textFieldFactory() {
        return new JFXTextField();
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setFormMode(FormMode formMode) {

    }

    @Override
    protected String[] comboBoxForFields() {
        return new String[]{"careFacility","bloodType"};
    }

    @Override
    protected ObservableList<String> listForField(String fieldName) {
        switch (fieldName){
            case "careFacility":
                return FXCollections.observableArrayList(Arrays.asList("מאוחדת","כללית","מכבי","לאומית"));
            case "bloodType":
                return FXCollections.observableArrayList(Arrays.asList("AB","B","A","O"));
        }
        return null;
    }

    @Override
    public String[] inupdateableFields() {
        return new String[]{"ID"};
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
    }
}
