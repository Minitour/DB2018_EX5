package network.api;

import model.Doctor;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class DoctorAPITest {

    DoctorAPI api = new DoctorAPI();
    String sessionToken;
    int accountId;

    @Test
    public void login() {

        LoginAPI api = new LoginAPI();

        String email = "tony@newsound.mobi";
        String password = "1212121212";

        api.login(email, password, (response, id, token, roleId) -> {
            assertTrue(token != null);
            assertTrue(id != 0);
            assertTrue(roleId != -1 && roleId != 0);
        });
    }

    @Test
    public void read() {
        AutoSignIn.ID = accountId;
        AutoSignIn.SESSION_TOKEN = sessionToken;

        Doctor d = new Doctor("343434343");
        api.read(d, (res,doctor) -> {
            assertTrue(doctor != null);
        });
    }

    @Test
    public void readAll() {
        api.readAll((response, items) -> {
            assertTrue(items != null);
        });
    }
}
