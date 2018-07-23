package network.api;

import model.Department;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class DepartmentAPITest {

    DepartmentAPI api = new DepartmentAPI();
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

        Department d = new Department(1002, 2);
        api.read(d, (res,dep) -> {
            assertTrue(dep != null);
        });
    }

    @Test
    public void readAll() {
        api.readAll((response, items) -> {
            assertTrue(items != null);
        });
    }
}
