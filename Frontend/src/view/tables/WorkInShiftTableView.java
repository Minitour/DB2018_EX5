package view.tables;

import com.jfoenix.controls.JFXSnackbar;
import model.Person;
import model.Shift;
import model.WorkInShift;
import network.api.ProfileAPI;
import network.api.ShiftAPI;
import network.api.WorkInShiftAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import utils.Response;
import view.forms.WorkInShiftForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.*;

public class WorkInShiftTableView extends GenericTableView<WorkInShift> {

    private WorkInShiftAPI api ;
    private List<WorkInShift> shifts = new ArrayList<>();
    private Map<Integer, Shift> allShifts = new HashMap<>();
    private Map<String,String> names = new HashMap<>();

    public WorkInShiftTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        WorkInShift shift = shifts.remove(index);
        api.delete(shift,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<WorkInShift> onView(int index) {
        WorkInShift shift = shifts.get(index);
        return new WorkInShiftForm(shift,this);
    }

    @Override
    protected UIFormView<WorkInShift> onInsert() {
        return new WorkInShiftForm(null, this);
    }

    @Override
    protected Class<WorkInShift> classType() {
        return WorkInShift.class;
    }

    @Override
    public Collection<? extends WorkInShift> dataSource() {
        return shifts;
    }

    @Override
    public void callback(WorkInShift value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> {

            JFXSnackbar bar = new JFXSnackbar(this);
            bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Success" : "Failed, response message: " + response.getMessage()));
            reloadDataFromServer();

        });
    }

    @Override
    public TableColumnValue<WorkInShift> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return ws -> names.get(ws.getDoctorID());
            case 1:
                return ws -> {
                    Shift s = allShifts.get(ws.getShiftNumber());
                    return s.dayLiteralValue() + ", " +s.typeLiteralValue();
                };
                default:return super.cellValueForColumnAt(index);
        }
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new WorkInShiftAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            if(response.isOK()) {
                new ShiftAPI().readAll((res, data) -> {
                    for (Shift shift : data)
                        allShifts.put(shift.getShiftNumber(),shift);

                    new ProfileAPI().readAll((response1, items1) -> {

                        for(Person p : items1)
                            names.put(p.getID(),p.getFirstName() + " " + p.getSurName());

                        shifts.clear();
                        shifts.addAll(items);
                        reloadData();
                    });
                });
            }
        });
    }
}
