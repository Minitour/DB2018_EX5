package view.generic;
import com.google.gson.annotations.Expose;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import ui.LocalizationManager;
import ui.UITableView;
import utils.AutoColor;
import utils.CSVExportRequest;
import utils.CSVExporter;
import view.DialogView;
import view.DynamicDialog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public abstract class GenericTableView<T> extends UITableView<T> implements UIFormView.OnFinish<T>,DynamicDialog.DialogDelegate{

    private DynamicDialog dialogView;

    /**
     * should show the delete column
     */
    private boolean delete;

    /**
     * should show the update column
     */
    private boolean update;

    /**
     * show show the insert button.
     */
    private boolean insert;

    private TableColumn<T,?> col_delete;
    private TableColumn<T,?> col_view;
    private Button addButton;
    private Button exportButton;

    public GenericTableView(boolean delete, boolean update, boolean insert) {
        super();
        this.delete = delete;
        this.update = update;
        this.insert = insert;

        col_delete = new TableColumn<>();
        col_view = new TableColumn<>();

        col_delete.setCellFactory(param -> new ButtonCell("Delete", this::onDelete,"button-delete"));
        col_view.setCellFactory(param -> new ButtonCell("View", index -> {
            UIFormView view = onView(index);
            dialogView = new DynamicDialog(view);
            dialogView.setTitle("Update Entry");
            dialogView.getPostiveButton().setText("Update");
            dialogView
                    .delegate(this)
                    .prepare()
                    .show(this);
        },"button-normal"));

        addButton = new JFXButton("+");
        addButton.getStyleClass().add("fab");
        addButton.setStyle("-fx-background-color: "+ AutoColor.secondaryColor+";");
        addButton.setOnAction(event -> {
            UIFormView form = onInsert();
            dialogView = new DynamicDialog(form);
            dialogView.setTitle("Add Entry");
            dialogView.getPostiveButton().setText("Add");
            dialogView
            .delegate(this)
            .prepare()
            .show(this);
        });

        exportButton = new JFXButton("Export");
        exportButton.getStyleClass().addAll("button-raised");
        exportButton.setStyle("-fx-background-color: "+ AutoColor.secondaryColor+";");
        exportButton.setOnAction(event -> makeExportRequest());

        getToolBar().getChildren().addAll(exportButton);

        if (update) {
            //show view column
            tableView.getColumns().add(col_view);
        }

        if (delete) {
            //show delete column
            tableView.getColumns().add(col_delete);
        }

        if (insert) {
            //show insert button
            getToolBar().getChildren().add(addButton);
        }


    }


    /**
     * Method called for deleting an object at certain index.
     *
     * @param index The index to delete.
     */
    protected abstract void onDelete(int index);

    protected abstract UIFormView<T> onView(int index);

    /**
     * Method called when table requests an insert view.
     */
    protected abstract UIFormView<T> onInsert();

    protected abstract Class<T> classType();

    //=========================================================================================================

    public static class ButtonCell extends TableCell {
        final Button cellButton;

        public ButtonCell(String title, ActionCallBack callBack,String styleClass) {
            cellButton = new JFXButton(title);
            cellButton.getStyleClass().add(styleClass);
            cellButton.setOnAction(t -> callBack.didSelectAction(ButtonCell.this.getIndex()));
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setTextFill(null);
                setGraphic(null);
                return;
            }

            if (!empty) {
                setAlignment(Pos.CENTER);
                setGraphic(cellButton);
            }
        }

        public interface ActionCallBack {
            void didSelectAction(int index);
        }

    }

    //=========================================================================================================

    @Override
    public void callback(T value) {
        if(dialogView != null)
            dialogView.close();
    }

    //=========================================================================================================

    @Override
    public void onHook(Map<String, Node> views, DynamicDialog dialog) {
        dialog.getNegativeButton().setText("Cancel");
        dialog.getNaturalButton().setText("Done");
        dialog.setMessage("");
    }

    @Override
    public boolean onDone(DynamicDialog dialog) {
        UIFormView<T> v = (UIFormView<T>) dialog.getContentView();
        T result = v.result();
        callback(result);
        return true;
    }

    @Override
    public void onCancel(DynamicDialog dialog) {

    }

    //=========================================================================================================


    List<Field> inferedFields;
    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        inferedFields = findFieldsAsList(Expose.class,classType());
        super.layoutSubviews(bundle);
    }

    @Override
    public int numberOfColumns() {
        return inferedFields.size();
    }

    @Override
    public String bundleIdForIndex(int index) {
        return transform(inferedFields.get(index).getName());
    }

    @Override
    public TableColumnValue<T> cellValueForColumnAt(int index) {
        return p -> {
            try {
                inferedFields.get(index).setAccessible(true);
                return inferedFields.get(index).get(p);
            } catch (IllegalAccessException e) {
                return null;
            }
        };
    }

    private List<Field> findFieldsAsList(Class<? extends Annotation> ann, Class<?> cls) {
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

    //=========================================================================================================

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

    private String[] convertRowToArray(int row){
        String[] arr = new String[numberOfColumns()];

        for(int i = 0; i< numberOfColumns();i++){
            arr[i] = getStringValue(row,i);
        }

        return arr;
    }

    private List<String[]> prepareDataForCSVExport(){
        List<String[]> list = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            list.add(convertRowToArray(i));
        }
        return list;
    }


    private String[] getCSVHeader(){
        String arr[] = new String[numberOfColumns()];
        for(int i = 0; i < arr.length;i++){
            arr[i] = bundleIdForIndex(i);
        }
        return arr;
    }

    private void makeExportRequest(){
        CSVExportRequest request = new CSVExportRequest(
                ("export_"+classType().getSimpleName()+"_"+System.currentTimeMillis()).toLowerCase()
                , prepareDataForCSVExport()
                ,getCSVHeader()
        );

        CSVExporter.export(request,(name,success) -> {
            Pane pane = getDelegate().getRoot();
            JFXSnackbar bar = new JFXSnackbar(pane);
            if (success)
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Successfully created "+name));
            else
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Failed to export "+name));
        });
    }

    protected String getStringValue(int row,int column){
        String value = null;
        try{
            ObservableValue s = ((TableColumn) tableView.getColumns().get(column)).getCellObservableValue(row);
            value = s.getValue().toString();
        }catch (ClassCastException e){
            value = ((TableColumn) tableView.getColumns().get(column)).getCellObservableValue(row).toString();
        }finally {
            return value
                    .replace(",","")
                    .replace("\"","")
                    .replace("\n"," ");
        }
    }
}
