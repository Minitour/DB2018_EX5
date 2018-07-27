package view.forms;

import model.Account;
import view.generic.UIFormView;

/**
 * Created By Tony on 26/07/2018
 */
public class AccountForm extends UIFormView<Account> {
    public AccountForm(Account existingValue, OnFinish<Account> callback) {
        super(Account.class, existingValue, callback);
    }
}
