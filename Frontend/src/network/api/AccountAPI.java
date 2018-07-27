package network.api;

import model.Account;
import network.generic.GenericAPI;
import utils.Constants;

/**
 * Created by Antonio Zaitoun on 27/07/2018.
 */
public class AccountAPI extends GenericAPI<Account> {

    public AccountAPI() {
        super(Constants.Routes.account(), Account.class);
    }
}
