package view.tables;

import model.Shift;
import network.api.ShiftAPI;
import view.forms.ShiftForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ShiftTableView extends GenericTableView<Shift> {

    private ShiftAPI api ;
    private List<Shift> shifts = new ArrayList<>();

    public ShiftTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    protected void onDelete(int index) {
        Shift shift = shifts.remove(index);
        api.delete(shift,response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Shift> onView(int index) {
        Shift shift = shifts.get(index);
        return new ShiftForm(shift,this);
    }

    @Override
    protected UIFormView<Shift> onInsert() {
        return new ShiftForm(null, this);
    }

    @Override
    protected Class<Shift> classType() {
        return Shift.class;
    }

    @Override
    public Collection<? extends Shift> dataSource() {
        return shifts;
    }

    @Override
    public void callback(Shift value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, response -> reloadDataFromServer());
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new ShiftAPI();
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
