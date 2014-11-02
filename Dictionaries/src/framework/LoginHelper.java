package framework;

import data.LoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by arseny on 22.10.14.
 */
public class LoginHelper extends BaseHelper {

    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public String doLogin(LoginData loginData) {
        manager.getNavigationHelper().gotoLoginPage();

        manager.wait.until(visibilityOfElementLocated(By.id(locators.getProperty("loginField"))));
        type(By.id(locators.getProperty("loginField")), loginData.getLogin());

        manager.wait.until(visibilityOfElementLocated(By.id(locators.getProperty("passwordField"))));
        type(By.id(locators.getProperty("passwordField")), loginData.getPass());

        manager.wait.until(visibilityOfElementLocated(By.cssSelector(locators.getProperty("loginButton"))));
        scrollAndClick(By.cssSelector(locators.getProperty("loginButton")));

        manager.wait.until(visibilityOfElementLocated(By.id(locators.getProperty("successLoginConfirmationElement"))));
        if (isElementPresent(By.id(locators.getProperty("successLoginConfirmationElement")))) {
            return "OK";
        }

        manager.wait.until(visibilityOfElementLocated(By.id(locators.getProperty("loginErrorField"))));
        return getText(By.id(locators.getProperty("loginErrorField")));
    }
}
