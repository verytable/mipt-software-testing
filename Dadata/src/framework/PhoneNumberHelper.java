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

            resultsOfProcessing.setInitialValue(getText(By.xpath(locators.getProperty("initialValue"))));
            resultsOfProcessing.setPhoneNumberType(getText(By.xpath(locators.getProperty("phoneNumberType"))));
            resultsOfProcessing.setOperator(getText(By.xpath(locators.getProperty("operator"))));
            resultsOfProcessing.setRegion(getText(By.xpath(locators.getProperty("region"))));
            resultsOfProcessing.setTimeZone(getText(By.xpath(locators.getProperty("timeZone"))));
        }
        return resultsOfProcessing;
    }
}
