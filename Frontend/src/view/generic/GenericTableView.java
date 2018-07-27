package view.generic;

import com.google.gson.annotations.Expose;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import ui.UITableView;
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
}
