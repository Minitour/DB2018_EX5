package view.generic;

import com.google.gson.annotations.Expose;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ui.UIView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @FXML
    private Button confirm;

    private Class<T> cls;

    private T existingValue;

    private OnFinish<T> callback = null;

    public UIFormView(Class<T> cls){
        this(cls,null,null);
    }

    public UIFormView(Class<T> cls,T existingValue){
        this(cls,existingValue,null);
    }

    public UIFormView(Class<T> cls,T existingValue,OnFinish<T> callback) {
        super("/resources/xml/base_form.fxml");
        this.cls = cls;
        this.callback = callback;

        title_label.setText(cls.getSimpleName() + " Form");

        confirm.setOnAction(event -> {

            T val = result();
            callback.callback(val);
        });

        if (callback == null) {
            //read only mode
            confirm.setVisible(false);
        }


        List<Field> fields = findFieldsAsList(Expose.class,cls);
        for (Field field : fields) {

            Class ofField = field.getType();
            if (ofField.equals(String.class) ||
                    ofField.equals(Integer.class) ||
                    ofField.equals(Float.class) ||
                    ofField.equals(Short.class)) {
                TextField tf = getTextField(field.getName());

                tf.setEditable(callback != null);

                if(existingValue != null){
                    try {
                        field.setAccessible(true);
                        Object val = field.get(existingValue);
                        tf.setText(String.valueOf(val));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                elements_vbox.getChildren().add(tf);
            }

            if (ofField.equals(Date.class) || ofField.equals(java.sql.Date.class)){

                DatePicker picker = getDatePicker(field.getName());
                picker.setEditable(callback != null);
                if(existingValue != null){
                    try {
                        field.setAccessible(true);
                        Object o =  field.get(existingValue);
                        Date da = null;
                        if(o instanceof java.sql.Date){
                            LocalDate localDate = ((java.sql.Date) o).toLocalDate();
                            picker.setValue(localDate);
                        }

                        if (o instanceof java.util.Date && !(o instanceof java.sql.Date)){
                            da = (Date) o;
                            LocalDate d = LocalDate.from(Instant.ofEpochMilli(da.getTime()).atZone(ZoneId.systemDefault()));
                            picker.setValue(d);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                elements_vbox.getChildren().add(picker);
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
    public boolean isValid(){ return true; }

    /**
     * A function used to clear the fields and reset them.
     */
    public void reset(){};

    /**
     * @return A result object from the form.
     */
    public T result()  {
        Constructor constructor = cls.getConstructors()[0];
        Object[] params = new Object[constructor.getParameterCount()];

        try {
            T obj = (T) constructor.newInstance(params);
            populateFields(obj);
            return obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void populateFields(T instance) {
        List<Field> fields = findFieldsAsList(Expose.class,instance.getClass());
        for (Field field : fields) {
            try {
                Class ofField = field.getType();

                Object value = null;

                if (ofField.equals(String.class) ||
                        ofField.equals(Integer.class) ||
                        ofField.equals(Short.class) ||
                        ofField.equals(Float.class)) {

                    TextField tf = (TextField) elements_vbox.lookup("#"+field.getName());

                    if (tf == null)
                        continue;

                    String data = tf.getText();

                    if(ofField.equals(String.class)){
                        value = data;
                    }

                    if(ofField.equals(Integer.class)){
                        try {
                            value = Integer.parseInt(data);
                        }catch (NumberFormatException e){ value = 0; }
                    }

                    if(ofField.equals(Float.class)){
                        try {
                            value = Float.parseFloat(data);
                        }catch (NumberFormatException e){ value = 0; }
                    }

                    if(ofField.equals(Short.class)){
                        try {
                            value = Short.parseShort(data);
                        }catch (NumberFormatException e){ value = 0; }
                    }
                }

                if (ofField.equals(Date.class) || ofField.equals(java.sql.Date.class)){

                    DatePicker picker = (DatePicker) elements_vbox.lookup("#"+field.getName());

                    if(picker == null)
                        continue;

                    LocalDate localDate = picker.getValue();
                    if(localDate != null) {
                        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                        value = Date.from(instant);
                    }
                    if(value == null)
                        value = new Date();
                }

                field.setAccessible(true);
                field.set(instance,value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the form mode to read only (view) or read+write (editable)
     * @param formMode
     */
    public void setFormMode(FormMode formMode){}

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

    public interface OnFinish<T> {
        void callback(T value);
    }
}
