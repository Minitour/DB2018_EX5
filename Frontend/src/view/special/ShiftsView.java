package view.special;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import model.Shift;
import network.api.ShiftAPI;
import ui.UITableView;
import ui.UIView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created By Tony on 26/07/2018
 */
public class ShiftsView extends UIView {

    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox vbox;

    //api wrapper
    private ShiftAPI api = new ShiftAPI();

    //items
    private List<Shift> list = new ArrayList<>();

    private Predicate<Shift> predicate = null;

    private UITableView<Shift> shiftsTableView = new UITableView<Shift>() {
        @Override
        public int numberOfColumns() {
            return 3;
        }

        @Override
        public Collection<? extends Shift> dataSource() {
            if (predicate == null)
                return list;

            return list.stream().filter(predicate).collect(Collectors.toList());
        }

        @Override
        public String bundleIdForIndex(int index) {
            switch (index){
                case 0: return "Shift Number";
                case 1: return "Day in Week";
                case 2: return "Shift Type";
            }
            return null;
        }

        @Override
        public TableColumnValue<Shift> cellValueForColumnAt(int index) {
            switch (index){
                case 0: return Shift::getShiftNumber;
                case 1: return Shift::getDayInWeek;
                case 2: return Shift::getDayInWeek;
                default:
                    return null;
            }
        }
    };

    public ShiftsView(){
        super("/resources/xml/view_shifts.fxml");
        vbox.getChildren().add(shiftsTableView);

        api.readAll((response, items) -> {
            this.list.clear();
            this.list.addAll(items);
            shiftsTableView.reloadData();
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue != null) {
                int dow = newValue.getDayOfWeek().getValue();
                dow += 1;
                if (dow > 7)
                    dow = 1;

                final int day = dow;
                predicate = shift -> Integer.parseInt(shift.getDayInWeek()) == day;
            }else
                predicate = null;

            shiftsTableView.reloadData();
        });
    }

}
