import controller.*;
import database.data_access.QueriesAccess;
import database.data_access.SessionAccess;
import model.join.HospitalJoinPerson;
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
        post("/department",new DepartmentController());
        post("/doctor",new DoctorsController());
        post("/event",new MedicalEventController());
        post("/eventInDep",new MedicalEventInDepartmentController());
        post("/hospital",new HospitalController());
        post("/hospitalized",new HospitalizedController());
        post("/login",new LoginController());
        post("/patient",new PatientsController());
        post("/profile",new ProfileController());
        post("/room",new RoomController());
        post("/shift",new ShiftController());
        post("/vacation",new DoctorVacationsController());
        post("/workInShift",new WorkInShiftController());
    }
}
