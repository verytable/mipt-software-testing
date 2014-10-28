package tests;

import data.DictionaryData;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

/**
 * Created by arseny on 27.10.14.
 */
public class DictionaryDeletionTests extends TestBase {

    @Test
    public void DeleteExistingDictionary() {
        app.getNavigationHelper().goToDictionariesPage();

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().removeDictionaryByDictionaryData(dictionaryData);

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Collections.sort(dictionaryDataList1);
        Collections.sort(dictionaryDataList2);

        assertEquals(dictionaryDataList1, dictionaryDataList2, "Creating and then deleting element");
    }
}
