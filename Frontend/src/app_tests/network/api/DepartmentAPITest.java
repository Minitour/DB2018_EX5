package network.api;

import model.Department;
import org.junit.Test;
import utils.AutoSignIn;

import static org.junit.Assert.assertTrue;

public class DepartmentAPITest {

    DepartmentAPI api = new DepartmentAPI();
    String sessionToken = "6Nmyqt6W9u7pNCARdnvZsw0qsSjcD0MJm9s2ybULzGdyxHKXA7QygFMAs3Yl5nRwkcKUKvzKcU8zbTBzvP4QNhHLUbBynwR0M9CPKXgQCPkx5UeaqRumT7fNsngPlGvP";
    int accountId = 15;

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
