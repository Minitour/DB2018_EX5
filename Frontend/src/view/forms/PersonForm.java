package view.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import model.Person;
import view.generic.UIFormView;

import java.sql.Date;

/**
 * Created By Tony on 26/07/2018
 */
public class PersonForm extends UIFormView<Person> {

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
}
