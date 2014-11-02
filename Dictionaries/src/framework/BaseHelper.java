package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by arseny on 22.10.14.
 */
public class BaseHelper {

    protected AppManager manager;
    protected WebDriver driver;

    protected Properties locators = new Properties();

    public BaseHelper(AppManager manager) {
        this.manager = manager;
        this.driver = manager.driver;
        String locatorsFile = "locators.properties";
        try {
            locators.load(new FileReader(new File(locatorsFile)));
        } catch (Exception ex) {
            System.err.println("problem with properties file.");
        }
    }

    protected void scrollAndClick(By by) {
        WebElement element = driver.findElement(by);
        int elementPosition = element.getLocation().getY();
        String js = String.format("window.scroll(0, %s)", elementPosition - 50);
        ((JavascriptExecutor)driver).executeScript(js);
        element.click();
    }

    protected void type(By by, String input) {
        driver.findElement(by).sendKeys(input);
    }

    protected String getText(By by) {
        return driver.findElement(by).getText();
    }

    protected boolean isElementPresent(By by) {
        try {
            List<WebElement> elements = driver.findElements(by);
            if (elements.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
        try {
            return driver -> {
                WebElement toReturn = driver.findElement(locator);
                if (toReturn.isDisplayed()) {
                    return toReturn;
                }
                return null;
            };
        } catch (StaleElementReferenceException ex) {
            return visibilityOfElementLocated(locator);
        }
    }

    public ExpectedCondition<List<WebElement>> visibilityOfElementsLocated(final By locator) {
        try {
            return driver -> {
                List<WebElement> elementsToReturn = driver.findElements(locator);
                boolean allDisplayed = true;
                for (WebElement element : elementsToReturn) {
                    if (!element.isDisplayed()) {
                        allDisplayed = false;
                    }
                }
                if (allDisplayed) {
                    return elementsToReturn;
                } else {
                    return null;
                }
            };
        } catch (StaleElementReferenceException ex) {
            return visibilityOfElementsLocated(locator);
        }
    }
}
