package network.api;

import model.Person;
import org.junit.Before;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

/**
 * Created By Tony on 23/07/2018
 */
public class PatientsAPITest {

    PatientsAPI api = new PatientsAPI();
    String sessionToken;
    int accountId;

    @Before
    public void setup() throws InterruptedException {
        LoginAPI api = new LoginAPI();

        String email = "asdasdasdasd";
        String password = "1212121212";
        api.login(email, password, (response, id, token, roleId) -> {
            sessionToken = token;
            accountId = id;
        });

        Thread.sleep(5000);
    }

    @Test
    public void read() {
        AutoSignIn.ID = accountId;
        AutoSignIn.SESSION_TOKEN = sessionToken;

        Person p = new Person("343434343");
        api.read(p, (res,person) -> {
            assertTrue(person != null);
        });
    }

    @Test
    public void readAll() {
        api.readAll((response, items) -> {
            assertTrue(items != null);
        });
    }

}