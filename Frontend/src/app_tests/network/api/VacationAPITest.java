package network.api;

import model.DoctorVacation;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class VacationAPITest {

    VacationAPI api = new VacationAPI();
    String sessionToken = "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP";
    int accountId = 15;
//        "ACCOUNT_ID": 15,
    //"SESSION_TOKEN": "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP"


    @Test
    public void read() {
        AutoSignIn.ID = accountId;
        AutoSignIn.SESSION_TOKEN = sessionToken;

        DoctorVacation m = new DoctorVacation("343434343");
        api.read(m, (res,event) -> {
            assertTrue(event != null);
        });
    }

    @Test
    public void readAll() {
        api.readAll((response, items) -> {
            assertTrue(items != null);
        });
    }
}
