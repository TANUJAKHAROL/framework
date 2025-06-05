package dataprovider;

import org.testng.annotations.DataProvider;
public class CreateOperationData {
    @DataProvider(name = "createOperationData")
    public Object[][] createOperationData() {
        return new Object[][]{
                {"2025-02-19T17:36:18.438Z", "Valid", 100.50, 1, "Account1", 1000.0, 1, "Label1"},
                {"2025-03-20T15:30:00.123Z", "Invalid", 200.75, 2, "Account2", 2000.0, 2, "Label2"},
                {"2025-04-22T11:25:30.567Z", "EdgeCase", 0.01, 3, "Account3", 3000.0, 3, "Label3"},
                {"2025-05-25T20:45:10.890Z", "BoundaryTest", 5000.00, 4, "Account4", 5000.0, 4, "Label4"},
                {"2025-06-30T23:59:59.999Z", "SpecialChars", -100.50, 5, "Account5", -1000.0, 5, "Label5"}
        };
    }
}