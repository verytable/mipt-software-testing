package framework;

import data.GroupData;
import data.LoginData;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;

/**
 * Created by arseny on 22.10.14.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(AppManager manager) {
        super(manager);
    }

    public void gotoLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(manager.baseUrl);
    }

    public void goToGroupsPage() {
        driver.get(manager.baseUrl);
        if (isElementPresent(By.cssSelector(locators.getProperty("loginButton")))) {
            LoginData loginData = new LoginData();
            loginData.setLogin(manager.properties.getProperty("login"))
                    .setPass(manager.properties.getProperty("pass"));

            assertEquals(manager.getLoginHelper().doLogin(loginData),"OK","Login with default params failed");
        } else {
            //goToGroupsPage();
        }
    }

    public void goToDictionariesPage() {
        manager.getNavigationHelper().goToGroupsPage();

        if (isElementPresent(By.linkText(manager.baseGroupName))) {
            assertEquals(isElementPresent(By.linkText(manager.baseGroupName)), true);
            click(By.linkText(manager.baseGroupName));
        } else {
            GroupData groupData = new GroupData();
            groupData.setName(manager.baseGroupName)
                    .setDescription("")
                    .setExpectedResult("OK");

            manager.getGroupHelper().createGroup(groupData);
        }
    }
}
