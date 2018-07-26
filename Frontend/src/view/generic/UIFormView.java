package view.generic;

import com.google.gson.annotations.Expose;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ui.UIView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 14/02/2018
 */
public abstract class UIFormView<T> extends UIView {

    @FXML
    private Label title_label;

    @FXML
    private VBox elements_vbox;

    private Class<T> cls;

    public UIFormView(Class<T> cls) {
        super("/resources/xml/base_form.fxml");
        this.cls = cls;
        title_label.setText(cls.getSimpleName() + " Form");

        List<Field> fields = findFieldsAsList(Expose.class,cls);
        for (Field field : fields) {

            Class ofField = field.getType();
            if (ofField.equals(String.class) ||
                    ofField.equals(Integer.class)) {
                elements_vbox.getChildren().add(getTextField(field.getName()));
            }

            if (ofField.equals(Date.class)){
                elements_vbox.getChildren().add(getDatePicker(field.getName()));
            }

        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
    }

    protected DatePicker datePickerFactory(){
        return new DatePicker();
    }

    protected TextField textFieldFactory(){
        return new TextField();
    }

    private DatePicker getDatePicker(String fieldName) {
        DatePicker picker = datePickerFactory();
        picker.setId(fieldName);
        picker.setPromptText(transform(fieldName));

        return picker;
    }

    private TextField getTextField(String fieldName){
        TextField textField = textFieldFactory();
        textField.setId(fieldName);

        //todo: normalize prompt text
        textField.setPromptText(transform(fieldName));
        return textField;
    }

    private List<Field> findFieldsAsList(Class<? extends Annotation> ann,Class<?> cls) {
        List<Field> list = new ArrayList<>();

        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    list.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        return list;
    }

    /**
     * A function that checks if all the form fields has valid input.
     * @return true if all the fields have valid input else false.
     */
    public abstract boolean isValid();

    /**
     * A function used to clear the fields and reset them.
     */
    public abstract void reset();

    /**
     * @return A result object from the form.
     */
    public abstract T result();

    /**
     * Set the form mode to read only (view) or read+write (editable)
     * @param formMode
     */
    public abstract void setFormMode(FormMode formMode);

    public enum FormMode{
        READ_WRITE,READ_ONLY
    }

    private String transform(String str){
        return captializeAllFirstLetter(convertCamelCaseToUnderscore(str).replaceAll("_"," "));
    }

    private String convertCamelCaseToUnderscore(String camelCase){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return camelCase
                .replaceAll(regex, replacement)
                .toLowerCase();
    }

    private String captializeAllFirstLetter(String name) {
        char[] array = name.toCharArray();
        array[0] = Character.toUpperCase(array[0]);

        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }

        return new String(array);
    }
}
