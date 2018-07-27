package database.data_access;

import database.Database;
import database.access.AccessException;
import database.access.GenericAccess;
import model.Account;

import java.util.List;

/**
 * Created By Tony on 19/07/2018
 */
public class AccountAccess extends GenericAccess<Account> {
    public AccountAccess() { super(Account.class,"Account"); }

    public AccountAccess(Database existingDatabase) {
        super(Account.class, "Account", existingDatabase);
    }

    @Override
    public List<Account> getById(Object... params) throws AccessException {
        return super.getById(params);
    }

    @Override
    public List<Account> getAll(Object... params) throws AccessException {
        return super.getAll(params);
    }
}
