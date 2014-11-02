package framework;

import data.LoginData;
import org.openqa.selenium.By;

/**
 * Created by arseny on 22.10.14.
 */
public class LoginHelper extends BaseHelper {

    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public String doLogin(LoginData loginData) {
        manager.getNavigationHelper().gotoLoginPage();
        type(By.id(locators.getProperty("loginField")), loginData.getLogin());
        type(By.id(locators.getProperty("passwordField")), loginData.getPass());
        scrollAndClick(By.cssSelector(locators.getProperty("loginButton")));

        if (isElementPresent(By.id(locators.getProperty("successLoginConfirmationElement")))) {
            return "OK";
        }

        return getText(By.id(locators.getProperty("loginErrorField")));
    }
}
