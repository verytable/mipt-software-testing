package mipt.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.io.File;
import java.util.Iterator;

/**
 * Created by arseny on 01.10.14.
 */
public class Wikipedia extends TestBase {

    @DataProvider
    public Iterator<Object[]> loginData() throws Exception {
        return wrapLoginForDataProvider(LoginDataLoader.loadDataFromCsvFile(new File("logintests.csv"))).iterator();
    }

    @DataProvider
    public Iterator<Object[]> signupData() throws Exception {
        return wrapSignupForDataProvider(SignupDataLoader.loadDataFromCsvFile(new File("signuptests.csv"))).iterator();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(LoginData loginData) throws Exception {
        assertEquals(doLogin(loginData), loginData.getExpectedResult(), "test login failed: ");
    }

    @Test(dataProvider = "signupData")
    public void testSignup(SignupData signupData) throws Exception {
        assertEquals(doSignup(signupData), signupData.getExpectedResult(), "test signup failed: ");
    }

    private String doLogin(LoginData loginData) {
        driver.get("http://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        driver.findElement(By.linkText("Войти")).click();
        driver.findElement(By.id("wpName1")).sendKeys(loginData.getLogin());
        driver.findElement(By.id("wpPassword1")).sendKeys(loginData.getPass());
        driver.findElement(By.id("wpLoginAttempt")).click();

        String result = "";

        try {
            return driver.findElement(By.cssSelector("strong")).getText();
        } catch (Exception e) {
            WebElement element =  driver.findElement(By.id("wpLoginAttempt"));
            if (element == null) {
                return "SUCCESS";
            } else {
                return null;
            }

        }
    }

    private String doSignup(SignupData signupData) {
        driver.get("http://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        driver.findElement(By.linkText("Создать учётную запись")).click();
        driver.findElement(By.id("wpName2")).sendKeys(signupData.getLogin());
        driver.findElement(By.id("wpPassword2")).sendKeys(signupData.getPass());
        driver.findElement(By.id("wpRetype")).sendKeys(signupData.getRetype());
        driver.findElement(By.id("wpCreateaccount")).click();

        String result = "";

        try {
            return driver.findElement(By.id("mw-createacct-status-area")).getText().replaceFirst("Ошибка создания учётной записи\n", "");
        } catch (Exception e) {
            WebElement element =  driver.findElement(By.id("wpLoginAttempt"));
            if (element == null) {
                return "SUCCESS";
            } else {
                return null;
            }

        }
    }

}
