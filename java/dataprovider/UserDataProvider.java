package dataprovider;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class UserDataProvider {
    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        return new Object[][]{
                {0, "tanujaa", "Tanuja", "Kharol", "tanuja@gmail.com", "string", true, "en", "admin", "2025-02-21T17:00:09.520Z", "admin", "2025-02-21T17:00:09.520Z", Arrays.asList("ROLE_USER"), "string"},
                {1, "john_doee", "John", "Doe", "john_doe@example.com", "string", true, "en", "admin", "2025-02-21T17:00:09.520Z", "admin", "2025-02-21T17:00:09.520Z", Arrays.asList("ROLE_USER"), "password123"}
        };
    }
}
