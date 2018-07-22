import controller.*;
import org.apache.log4j.BasicConfigurator;
import utils.Permissions;
import utils.RESTApplication;

import java.io.IOException;

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
        post("/patient",new PatientsController());
        post("/doctor",new DoctorsController());
        post("/vacation",new DoctorVacationsController());
    }
}
