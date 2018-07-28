package view.generic;

import com.google.gson.annotations.Expose;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ui.UIView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created By Tony on 14/02/2018
 */
public abstract class UIFormView<T> extends UIView {

    /**
     * The title label of this form.
     */
    @FXML
    protected Label title_label;

    /**
     * The vbox that holds the items of the form.
     */
    @FXML
    protected VBox elements_vbox;

    /**
     * confirm button.
     */
    @FXML
    private Button confirm;

    /**
     * The class type of this form
     */
    private Class<T> cls;

    /**
     * The existing value of this form.
     */
    private T existingValue;

    /**
     * The callback delegate.
     */
    private OnFinish<T> callback = null;

    /**
     * A map that contains a list of observable values used to display on the combo box.
     */
    private Map<String,ObservableList<ComboItem>> comboItems = new HashMap<>();

    /**
     * Constructor from class only.
     * @param cls the class of the
     */
    public UIFormView(Class<T> cls){
        this(cls,null);
    }

    /**
     * @param cls The class of the form.
     * @param existingValue An existing value to show.
     */
    public UIFormView(Class<T> cls,T existingValue){
        this(cls,existingValue,null,true);
    }

    /**
     * @param cls The class of the form.
     * @param existingValue An existing value to show.
     * @param callback The callback once user is done with the form.
     */
    public UIFormView(Class<T> cls,T existingValue,OnFinish<T> callback){
        this(cls,existingValue,callback,true);
    }

    /**
     *
     * @param cls The class of the form.
     * @param existingValue An existing value to show.
     * @param callback The callback once user is done with the form.
     * @param useAlternativeButton Use alternative submit button.
     */
    public UIFormView(Class<T> cls,T existingValue,OnFinish<T> callback,boolean useAlternativeButton) {
        super("/resources/xml/base_form.fxml");
        this.cls = cls;
        this.callback = callback;

        title_label.setText(cls.getSimpleName() + " Form");

        confirm.setOnAction(event -> {

            T val = result();
            callback.callback(val);
        });

        //read only mode
        confirm.setVisible(!useAlternativeButton);

        List<Field> fields = findFieldsAsList(Expose.class,cls);
        Set<String> notEditable = new HashSet<>(Arrays.asList(inupdateableFields()));
        Set<String> comboFields = new HashSet<>(Arrays.asList(comboBoxForFields()));
        Set<String> uninsertableFields = new HashSet<>(Arrays.asList(defaultValueFields()));
        Set<String> protectedFields = new HashSet<>(Arrays.asList(protectedFields()));

        //MARK: field creation
        for (Field field : fields) {

            //get field type
            Class ofField = field.getType();

            //get field name
            String fieldName = field.getName();


            //if this is a new form and this is not an insert-able field, skip to the next field.
            if(existingValue == null &&
                    uninsertableFields.contains(fieldName)){
                continue;
            }

            //check if value is of type combo box
            if(comboFields.contains(fieldName)) {

                //get observable list for combo box.
                ObservableList<ComboItem> observableList = listForField(fieldName);

                //check if not null
                if(observableList != null){

                    //store list in map
                    comboItems.put(fieldName,observableList);

                    //create combobox
                    StringComboBox comboBox = getComboBox(fieldName,observableList);



                    //add change listener
                    comboBox.valueProperty().addListener((observable, oldValue, newValue)
                            -> didComboSelectionChanged(fieldName,newValue));

                    //extract value if exists
                    extract(field, existingValue, value -> {

                        if(value != null) {
                            if(notEditable.contains(fieldName))
                                comboBox.setDisable(true);

                            if (observableList.isEmpty()) {
                                observableList.addListener(new ListChangeListener<ComboItem>() {
                                    @Override
                                    public void onChanged(Change<? extends ComboItem> c) {
                                        c.next();
                                        ComboItem added = c.getList().get(c.getTo() - 1);
                                        boolean isEqual = value.equals(String.valueOf(added.value));
                                        //comboBox.getSelectionModel().select(index >= 0 ? index : 0);
                                        if (isEqual) {
                                            comboBox.getSelectionModel().select(added);
                                            observableList.removeListener(this);
                                        }
                                    }
                                });
                            }else {
                                int index = observableList.indexOf(new ComboItem(value));
                                comboBox.getSelectionModel().select(index);
                            }
                        }


                    });

                    //add combo box to view
                    elements_vbox.getChildren().add(comboBox);
                    comboBox.prefWidthProperty().bind(elements_vbox.widthProperty());
                    continue;
                }

                //else fallthrough and create as normal field.
            }

            //check if field is string or number
            if (isStringOrNumber(ofField)) {

                //create a text field.
                TextField tf = getTextField(field.getName(),protectedFields.contains(fieldName));

                //check if there is a present value
                if(existingValue != null){
                    if(notEditable.contains(field.getName()))
                        tf.setEditable(false);

                    extract(field,existingValue, tf::setText);
                }

                //add field to the view
                elements_vbox.getChildren().add(tf);
                tf.prefWidthProperty().bind(elements_vbox.widthProperty());
            }

            //if normal date or sql date
            if (ofField.equals(Date.class) || ofField.equals(java.sql.Date.class)){

                // create a date picker
                DatePicker picker = getDatePicker(field.getName());

                //add populate with existing value if present
                if(existingValue != null){
                    if(notEditable.contains(field.getName()))
                        picker.setEditable(false);

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

                //add field
                elements_vbox.getChildren().add(picker);
                picker.prefWidthProperty().bind(elements_vbox.widthProperty());
            }
        }



    }

    protected DatePicker datePickerFactory(){
        return new DatePicker();
    }

    protected TextField textFieldFactory(){
        return new TextField();
    }

    protected TextField passwordTextFieldFactory() {
        return new PasswordField();
    }

    private DatePicker getDatePicker(String fieldName) {
        DatePicker picker = datePickerFactory();
        picker.setId(fieldName);
        picker.setPromptText(transform(fieldName));

        return picker;
    }

    private TextField getTextField(String fieldName,boolean password){
        TextField textField = password ? passwordTextFieldFactory() : textFieldFactory();
        textField.setId(fieldName);

        textField.setPromptText(transform(fieldName));
        return textField;
    }

    private StringComboBox getComboBox(String fieldName,ObservableList<ComboItem> list){
        StringComboBox comboBox = new StringComboBox();
        comboBox.setId(fieldName);
        comboBox.setItems(list);
        comboBox.setPromptText(transform(fieldName));
        return comboBox;
    }

    private boolean isStringOrNumber(Class<?> ofField){
       return ofField.equals(String.class) ||
                ofField.equals(Integer.class) ||
                ofField.equals(Float.class) ||
                ofField.equals(Short.class);
    }

    private void extract(Field field,Object o, PopulateCallback callback){
        if(o != null) {
            try {
                field.setAccessible(true);
                Object val = field.get(o);
                String strValue = String.valueOf(val);
                callback.extracted(strValue.equals("null") ? "" : strValue);
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    @FunctionalInterface
    private interface PopulateCallback{
        void extracted(String value);
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
     * A function that checks if all the form  has valid input.
     * @return true if all the fields have valid input else false.
     */
    public boolean isValid(){ return true; }

    /**
     * A function used to clear the fields and reset them.
     */
    public void reset(){
        elements_vbox.getChildren().forEach(node -> {
            if(node instanceof TextField){
                TextField tf = (TextField) node;
                tf.setText("");
                return;
            }

            if(node instanceof ComboBox){
                ComboBox comboBox = (ComboBox) node;
                comboBox.getSelectionModel().clearSelection();
                return;
            }

            if(node instanceof DatePicker){
                DatePicker datePicker = (DatePicker) node;
                datePicker.getEditor().setText("");
            }
        });
    };

    /**
     * @return A list of fields that cannot be updated.
     */
    public String[] inupdateableFields(){
        return new String[]{};
    }

    /**
     * @return A list of fields that cannot be inserted (they are automatically generated, like account id).
     */
    public String[] defaultValueFields() { return new String[]{};}

    /**
     * @return A list of fields that should be treated as a combo box.
     */
    protected String[] comboBoxForFields() {
        return new String[]{};
    }

    protected String[] hiddenFields() { return new String[]{}; }

    protected String[] protectedFields() {return new String[]{};}
    /**
     *
     * @param fieldName The field for which the combo box will display the list.
     * @return An observable list for the combo box to show.
     */
    protected ObservableList<ComboItem> listForField(String fieldName){
        return null;
    }

    /**
     * The default value for a field name that cannot be inserted.
     * @param fieldName
     * @return
     */
    protected Object defaultValueForField(String fieldName) { return null; }

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

    protected void didComboSelectionChanged(String nameField,ComboItem value){ }

    private void populateFields(T instance) {
        List<Field> fields = findFieldsAsList(Expose.class,instance.getClass());
        Set<String> uninsertableFields = new HashSet<>(Arrays.asList(defaultValueFields()));

        Set<String> hiddenFields = new HashSet<>(Arrays.asList(hiddenFields()));


        for (Field field : fields) {
            try {
                Class ofField = field.getType();

                Object value = null;


                //if the field is un-insertable and component does not exist:
                // meaning we are inserting and not updating
                if(uninsertableFields.contains(field.getName()) &&
                        elements_vbox.lookup("#"+field.getName()) == null){
                    //populate with default value.
                    value = defaultValueForField(field.getName());
                    field.setAccessible(true);
                    field.set(instance,value);
                    continue;
                }

                if(comboItems.containsKey(field.getName())){
                    StringComboBox comboBox = (StringComboBox) elements_vbox.lookup("#"+field.getName());
                    String data = comboBox.getSelectedValue();

                    if(ofField.equals(String.class)){
                        if (data.length() != 0)
                            value = data;
                        else
                            value = null;
                    }

                    if(ofField.equals(Integer.class)){
                        try {
                            value = Integer.parseInt(data);
                        }catch (NumberFormatException e){ value = null; }
                    }

                    if(ofField.equals(Float.class)){
                        try {
                            value = Float.parseFloat(data);
                        }catch (NumberFormatException e){ value = null; }
                    }

                    if(ofField.equals(Short.class)){
                        try {
                            value = Short.parseShort(data);
                        }catch (NumberFormatException e){ value = null; }
                    }
                    field.setAccessible(true);
                    field.set(instance,value);
                    continue;
                }

                if (ofField.equals(String.class) ||
                        ofField.equals(Integer.class) ||
                        ofField.equals(Short.class) ||
                        ofField.equals(Float.class)) {

                    TextField tf = (TextField) elements_vbox.lookup("#"+field.getName());


                    if (tf == null)
                        continue;

                    String data = tf.getText();

                    if(ofField.equals(String.class)){
                        if (data.length() != 0)
                            value = data;
                        else
                            value = null;
                    }

                    if(ofField.equals(Integer.class)){
                        try {
                            value = Integer.parseInt(data);
                        }catch (NumberFormatException e){ value = null; }
                    }

                    if(ofField.equals(Float.class)){
                        try {
                            value = Float.parseFloat(data);
                        }catch (NumberFormatException e){ value = null; }
                    }

                    if(ofField.equals(Short.class)){
                        try {
                            value = Short.parseShort(data);
                        }catch (NumberFormatException e){ value = null; }
                    }
                }

                if (ofField.equals(java.sql.Date.class)){

                    DatePicker picker = (DatePicker) elements_vbox.lookup("#"+field.getName());

                    if(picker == null)
                        continue;

                    LocalDate localDate = picker.getValue();
                    if(localDate != null) {
                        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                        value = new java.sql.Date(Date.from(instant).getTime());
                    }

                    if(value == null) {
                        value = new java.sql.Date(System.currentTimeMillis());
                    }
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

    private class StringComboBox extends ComboBox<ComboItem> {
        public String getSelectedValue(){
            try {
                ComboItem item = getSelectionModel().getSelectedItem();
                return String.valueOf(item.value);
            }catch (NullPointerException e){
                return null;
            }
        }

    }

    public static class ComboItem {
        public String displayName;
        public Object value;

        public ComboItem(String displayName, Object value) {
            this.displayName = displayName;
            this.value = value;
        }

        public ComboItem(String displayNameAndValue){
            this.displayName = displayNameAndValue;
            this.value = displayNameAndValue;
        }

        @Override
        public String toString() {
            return displayName;
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null &&
                    obj instanceof ComboItem
                    && (((ComboItem) obj).displayName.equals(displayName) || String.valueOf(((ComboItem) obj).value).equals(String.valueOf(value)));
        }
    }
}
