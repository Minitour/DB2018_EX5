package view.tables;

import model.WorkInShift;
import network.api.WorkInShiftAPI;
import view.forms.WorkInShiftForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class WorkInShiftTableView extends GenericTableView<WorkInShift> {

    private WorkInShiftAPI api ;
    private List<WorkInShift> shifts = new ArrayList<>();

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
        api.upsert(value, response -> reloadDataFromServer());
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
            shifts.clear();
            shifts.addAll(items);
            reloadData();
        });
    }
}
