package network.api;

import model.Doctor;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class DoctorAPITest {

    DoctorAPI api = new DoctorAPI();
    String sessionToken = "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP";
    int accountId = 15;
//        "ACCOUNT_ID": 15,
        //"SESSION_TOKEN": "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP"


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