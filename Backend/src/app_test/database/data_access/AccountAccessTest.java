package database.data_access;

import model.Account;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 20/07/2018.
 */
class AccountAccessTest {

    @Test
    void test(){
        try(AccountAccess accountAccess = new AccountAccess()) {
            List<Account> accounts = accountAccess.getAll(0);

            System.out.println(accounts);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}