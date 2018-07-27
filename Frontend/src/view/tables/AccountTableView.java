package view.tables;

import model.Account;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.Collection;

public class AccountTableView extends GenericTableView<Account> {


    @Override
    protected void onDelete(int index) {

    }

    @Override
    protected UIFormView<Account> onView(int index) {
        return null;
    }

    @Override
    protected UIFormView<Account> onInsert() {
        return null;
    }

    @Override
    protected Class<Account> classType() {
        return null;
    }

    @Override
    public Collection<? extends Account> dataSource() {
        return null;
    }
}
