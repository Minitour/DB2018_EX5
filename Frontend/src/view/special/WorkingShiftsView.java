package view.special;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import model.Person;
import model.Shift;
import model.WorkInShift;
import network.api.ProfileAPI;
import network.api.ShiftAPI;
import network.api.WorkInShiftAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import ui.UIView;
import utils.AutoSignIn;
import utils.Response;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created By Tony on 27/07/2018
 */
public class WorkingShiftsView extends UIView{
    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox vbox;

    //api wrapper
    private ShiftAPI api = new ShiftAPI();
    private WorkInShiftAPI api2 = new WorkInShiftAPI();

    //items
    private List<WorkInShift> list = new ArrayList<>();
    private Map<Integer,Shift> shifts = new HashMap<>();

    private Predicate<WorkInShift> predicate = null;

    private UITableView<WorkInShift> shiftsTableView = new UITableView<WorkInShift>() {
        @Override
        public int numberOfColumns() {
            return 2;
        }

        @Override
        public Collection<? extends WorkInShift> dataSource() {
            if (predicate == null)
                return list;

            return list.stream().filter(predicate).collect(Collectors.toList());
        }

        @Override
        public String bundleIdForIndex(int index) {
            switch (index){
                case 0: return "ID";
                case 1: return "Shift ID";
            }
            return null;
        }

        @Override
        public TableColumnValue<WorkInShift> cellValueForColumnAt(int index) {
            switch (index){
                case 0: return WorkInShift::getDoctorID;
                case 1: return WorkInShift::getShiftNumber;
                default:
                    return null;
            }
        }
    };

    public WorkingShiftsView(){
        super("/resources/xml/view_shifts.fxml");

        vbox.getChildren().add(shiftsTableView);

        new ProfileAPI().read(new Person(), (response1, object) -> {

            api.readAll((response, items) -> {
                for (Shift item : items) {
                    shifts.put(item.getShiftNumber(),item);
                }
            });

            api2.readAll(new WorkInShift(object.getID(),null),(response, items) -> {
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
                    predicate = shift -> Integer.parseInt(shifts.get(shift.getShiftNumber()).getDayInWeek()) == day;
                }else
                    predicate = null;

                shiftsTableView.reloadData();
            });
        });

    }
}
