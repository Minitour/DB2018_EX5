package network.api;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created By Tony on 23/07/2018
 */
public class LoginAPITest {

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
}