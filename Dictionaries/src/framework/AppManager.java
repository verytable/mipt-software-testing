package framework;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by arseny on 22.10.14.
 */
public class AppManager {

    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl;
    public String baseGroupName;
    public Properties properties;

    private LoginHelper loginHelper;
    private GroupHelper groupHelper;
    private DictionaryHelper dictionaryHelper;
    private NavigationHelper navigationHelper;

    public AppManager(Properties properties) throws Exception {
        String browser = properties.getProperty("browser");
        switch (browser) {
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            case "opera":
                driver = new OperaDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/home/arseny/IdeaProjects/lib/chromedriver");
                driver = new ChromeDriver();
                break;
            default:
                throw new Exception("Unknown browser");
        }

        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 30);

        this.baseUrl = properties.getProperty("baseUrl");
        this.baseGroupName = properties.getProperty("baseGroupName");
        driver.get(baseUrl);
        this.properties = properties;
    }

    public GroupHelper getGroupHelper() {
        if (groupHelper == null) {
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }

    public DictionaryHelper getDictionaryHelper() {
        if (dictionaryHelper == null) {
            dictionaryHelper = new DictionaryHelper(this);
        }
        return dictionaryHelper;
    }

    public LoginHelper getLoginHelper() {
        if (loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
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
