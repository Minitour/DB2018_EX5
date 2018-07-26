package view;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import model.Person;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class PersonForm extends UIFormView<Person> {

    public PersonForm() {
        super(Person.class);
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
    public Person result() {
        return null;
    }

    @Override
    public void setFormMode(FormMode formMode) {

    }
}
