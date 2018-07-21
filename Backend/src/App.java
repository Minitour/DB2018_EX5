import controller.LoginController;
import controller.ProfileController;
import database.data_access.AccountAccess;
import database.data_access.PatientAccess;
import database.data_access.SessionAccess;
import database.data_access.ShiftAccess;
import model.Account;
import model.Person;
import model.Session;
import model.Shift;
import org.apache.log4j.BasicConfigurator;
import utils.Permissions;
import utils.RESTApplication;
import utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Permission;
import java.sql.Date;
import java.util.List;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class App extends RESTApplication {

    public static void main(String... args) throws IOException {
        Permissions.init();
        BasicConfigurator.configure();
        launch(8080);
    }

    @Override
    public void init() {
        post("/login",new LoginController());
        post("/profile",new ProfileController());
    }
}
