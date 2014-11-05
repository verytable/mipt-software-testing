package mipt.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arseny on 08.10.14.
 */
public class TestBase {
    protected WebDriver driver;

    @BeforeClass
    public void testInit() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public static List<Object[]> wrapLoginForDataProvider(List<LoginData> loginsInput) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (LoginData login : loginsInput) {
            list.add(new Object[]{login});
        }

        return list;
    }

    public static List<Object[]> wrapSignupForDataProvider(List<SignupData> signupsInput) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (SignupData signup : signupsInput) {
            list.add(new Object[]{signup});
        }

        return list;
    }
}
