package dataprovider;

import org.testng.annotations.DataProvider;

public class ProvideBankAccountData {
    @DataProvider(name = "bankAccountData")
    public Object[][] provideBankAccountData() {
        return new Object[][]{
                {"Updated Account 1", 1500.0, 1},
                {"Updated Account 2", 2000.0, 2},
                {"Updated Account 3", 2500.0, 3}
        };
    }
}