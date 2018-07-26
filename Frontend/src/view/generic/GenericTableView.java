package view.generic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import ui.UITableView;

import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public abstract class GenericTableView<T> extends UITableView<T> implements UIFormView.OnFinish<T>{

    static int arc = 0;
    final int counter = arc++;
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

        col_delete.setCellFactory(param -> new ButtonCell("Delete", this::onDelete));
        col_view.setCellFactory(param -> new ButtonCell("View", this::onView));

        addButton = new Button("Add");
        addButton.setOnAction(event -> onInsert());


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
    protected void onDelete(int index) {

    }

    protected UIFormView<T> onView(int index) {
        return null;
    }

    /**
     * Method called when table requests an insert view.
     */
    protected UIFormView<T> onInsert() {
        return null;
    }

    public static class ButtonCell extends TableCell {
        final Button cellButton;

        public ButtonCell(String title, ActionCallBack callBack) {
            cellButton = new Button(title);
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
}
