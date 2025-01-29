package Tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestListener;
import utils.dataUtil;

import java.util.HashMap;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{

    @Test(dataProviderClass = dataUtil.class, dataProvider = "login",groups = {"smoke","Regression"},description = "login to the system")
    public void loginToTheSystem(HashMap<String, String> hashMap) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(hashMap.get("username"),hashMap.get("password"));
    }
}
