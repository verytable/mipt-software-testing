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
        type(By.id("j_username"), loginData.getLogin());
        type(By.id("j_password"), loginData.getPass());
        click(By.cssSelector("button.btn-login"));

        if (isElementPresent(By.id("header-link-logout"))) {
            return "OK";
        }

        return getText(By.id("div.text-error"));
    }
}
