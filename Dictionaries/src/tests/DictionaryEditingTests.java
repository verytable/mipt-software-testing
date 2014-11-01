package tests;

import data.DictionaryData;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by arseny on 29.10.14.
 */
public class DictionaryEditingTests extends TestBase {

    @Test
    public void UniqueRenaming() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().createDictionary(dictionaryData);

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().goToDictionaryPage(dictionaryData);
        app.getDictionaryHelper().editDictionaryName(dictionaryData, "new" + dictionaryData.getName());

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getName(),
                "new" + dictionaryData.getName(),
                "Editing with unique name");
    }
}
