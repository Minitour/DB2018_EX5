package database.data_access;

import model.Account;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Antonio Zaitoun on 20/07/2018.
 */
class AccountAccessTest {

    @Test
    void test(){
        try(AccountAccess accountAccess = new AccountAccess()) {
            List<Account> accounts = accountAccess.getAll();

            System.out.println(accounts);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}