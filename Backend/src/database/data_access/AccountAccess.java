package database.data_access;

import database.Database;
import database.access.GenericAccess;
import model.Account;

/**
 * Created By Tony on 19/07/2018
 */
public class AccountAccess extends GenericAccess<Account> {
    public AccountAccess() { super(Account.class,"Account"); }

    public AccountAccess(Database existingDatabase) {
        super(Account.class, "Account", existingDatabase);
    }
}
