import controller.LoginController;
import database.data_access.AccountAccess;
import database.data_access.SessionAccess;
import database.data_access.ShiftAccess;
import model.Account;
import model.Session;
import model.Shift;
import org.apache.log4j.BasicConfigurator;
import utils.RESTApplication;
import utils.Utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class App extends RESTApplication {

    public static void main(String... args){

        try(SessionAccess session_db = new SessionAccess();
            AccountAccess account_db = new AccountAccess(session_db)){


        } catch (Exception e) {
            e.printStackTrace();
        }


        BasicConfigurator.configure();
        launch(8080);
    }

    @Override
    public void init() {
        post("/login",new LoginController());
    }
}
