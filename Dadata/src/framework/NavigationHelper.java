package framework;

/**
 * Created by arseny on 05.11.14.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(AppManager manager) {
        super(manager);
    }

    public void gotoMainPage() {
        driver.manage().deleteAllCookies();
        driver.get(manager.baseUrl);
    }


}
