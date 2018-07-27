package network.api;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created By Tony on 23/07/2018
 */
public class AuthAPITest {

    @Test
    public void login() {

        AuthAPI api = new AuthAPI();

        String email = "tony@newsound.mobi";
        String password = "1212121212";

        api.login(email, password, (response, id, token, roleId) -> {
            assertTrue(token != null);
            assertTrue(id != 0);
            assertTrue(roleId != -1 && roleId != 0);
        });
    }
}