package framework;

import data.PhoneData;
import data.ResultsOfProcessing;
import org.openqa.selenium.By;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneNumberHelper extends BaseHelper {

    public PhoneNumberHelper(AppManager manager) {
        super(manager);
    }

    public ResultsOfProcessing processPhoneNumber(PhoneData phoneData) {
        manager.getNavigationHelper().gotoMainPage();
        type(By.id(locators.getProperty("phoneField")), phoneData.getPhoneNumber());
        scrollAndClick(By.cssSelector(locators.getProperty("submitPhoneDataButton")));

        ResultsOfProcessing resultsOfProcessing = new ResultsOfProcessing();
        if (isElementPresent(By.id(locators.getProperty("processingResultsTable")))) {

            resultsOfProcessing.setInitialValue(manager.driver.findElement(By.xpath(locators.getProperty("initialValue"))).getText());
            resultsOfProcessing.setPhoneNumberType(manager.driver.findElement(By.xpath(locators.getProperty("phoneNumberType"))).getText());
            resultsOfProcessing.setOperator(manager.driver.findElement(By.xpath(locators.getProperty("operator"))).getText());
            resultsOfProcessing.setRegion(manager.driver.findElement(By.xpath(locators.getProperty("region"))).getText());
            resultsOfProcessing.setTimeZone(manager.driver.findElement(By.xpath(locators.getProperty("timeZone"))).getText());
        }
        return resultsOfProcessing;
    }
}
