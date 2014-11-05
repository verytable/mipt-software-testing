package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by arseny on 05.11.14.
 */
public class AppManager {

    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl;
    public Properties properties;

    private PhoneNumberHelper phoneNumberHelper;
    private NavigationHelper navigationHelper;

    public AppManager(Properties properties) throws Exception {
        String browser = properties.getProperty("browser");
        switch (browser) {
            case "firefox" :
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new Exception("Unknown browser");
        }

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 30);

        this.baseUrl = properties.getProperty("baseUrl");
        driver.get(baseUrl);
        this.properties = properties;
    }

    public PhoneNumberHelper getDictionaryHelper() {
        if (phoneNumberHelper == null) {
            phoneNumberHelper = new PhoneNumberHelper(this);
        }
        return phoneNumberHelper;
    }

    public NavigationHelper getNavigationHelper() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public void stop() {
        driver.close();
        driver.quit();
    }
}
