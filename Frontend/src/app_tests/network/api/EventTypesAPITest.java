package network.api;

import model.MedicalEventTypes;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class EventTypesAPITest {

    EventTypesAPI api = new EventTypesAPI();
    String sessionToken = "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP";
    int accountId = 15;
//        "ACCOUNT_ID": 15,
    //"SESSION_TOKEN": "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP"


    @Test
    public void read() {
        AutoSignIn.ID = accountId;
        AutoSignIn.SESSION_TOKEN = sessionToken;

        MedicalEventTypes m = new MedicalEventTypes(1);
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
