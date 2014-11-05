package tests;

import data.PhoneData;
import data.PhoneDataLoader;
import data.ResultsOfProcessing;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneProcessingTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> dataProvider() throws IOException {
        return wrapPhoneNumberForDataProvider(data("phoneNumbers.csv"));
    }

    private List<PhoneData> data(String filename) throws IOException {
        return new PhoneDataLoader().loadDataFromCsvFile(filename);
    }

    @Test(dataProvider = "dataProvider")
    public void testProcessingPhoneData(PhoneData phoneData) {
        app.getNavigationHelper().gotoMainPage();
        ResultsOfProcessing resultsOfProcessing = app.getDictionaryHelper().processPhoneNumber(phoneData);
        assertEquals(resultsOfProcessing, phoneData.getExpectedResult());
    }
}
