import com.google.gson.annotations.Expose;
import controller.*;
import database.data_access.PatientAccess;
import database.data_access.PersonAccess;
import database.data_access.QueriesAccess;
import database.data_access.SessionAccess;
import model.Person;
import model.join.HospitalJoinPerson;
import utils.CSVExporter;
import utils.Permissions;
import utils.RESTApplication;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class App extends RESTApplication {

    public static void main(String... args) throws IOException {

        Permissions.init();
        //BasicConfigurator.configure();
        launch(8080);

    }

    @Override
    public void init() {
        post("/department",new DepartmentController());
        post("/doctor",new DoctorsController());
        post("/event",new MedicalEventController());
        post("/eventInDep",new MedicalEventInDepartmentController());
        post("/eventTypes",new MedicalEventTypesController());
        post("/hospital",new HospitalController());
        post("/hospitalized",new HospitalizedController());
        post("/login",new LoginController());
        post("/patient",new PatientsController());
        post("/profile",new ProfileController());
        post("/room",new RoomController());
        post("/shift",new ShiftController());
        post("/vacation",new DoctorVacationsController());
        post("/workInShift",new WorkInShiftController());
        post("/updatePassword",new UpdateAccountPasswordController());
        post("/checkedBy",new CheckedByController());
        post("/account",new AccountController());
        post("/payment",new PaymentController());
        post("/dashboard",new DashboardController());
    }
}
