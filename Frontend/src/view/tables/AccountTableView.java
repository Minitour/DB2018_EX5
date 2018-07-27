package view.tables;

import model.Account;
import network.api.AccountAPI;
import ui.UITableView;
import view.forms.AccountForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class AccountTableView extends GenericTableView<Account> {

    private AccountAPI api;
    private List<Account> list = new ArrayList<>();

    public AccountTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    @Override
    public int numberOfColumns() {
        return 4;
    }

    @Override
    public String bundleIdForIndex(int index) {
        switch (index){
            case 0: return "Account ID";
            case 1: return "Email";
            case 2: return "Role ID";
            case 3: return "Hospital ID";
        }
        return null;
    }

    @Override
    public TableColumnValue<Account> cellValueForColumnAt(int index) {
        switch (index){
            case 0:
                return Account::getACCOUNT_ID;
            case 1:
                return Account::getEMAIL;
            case 2:
                return Account::getROLE_ID;
            case 3:
                return Account::getHospitalID;

            default:return null;
        }
    }

    @Override
    protected void onDelete(int index) {
        api.delete(list.remove(index),response -> reloadDataFromServer());
    }

    @Override
    protected UIFormView<Account> onView(int index) {
        return new AccountForm(list.get(index),this);
    }

    @Override
    protected UIFormView<Account> onInsert() {
        return new AccountForm(null,this);
    }

    @Override
    protected Class<Account> classType() {
        return Account.class;
    }

    @Override
    public Collection<? extends Account> dataSource() {
        return list;
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new AccountAPI();
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            list.clear();
            list.addAll(items);
            reloadData();
        });
    }

    @Override
    public void callback(Account value) {
        super.callback(value);
        api.upsert(value, response -> reloadDataFromServer());
    }
}
