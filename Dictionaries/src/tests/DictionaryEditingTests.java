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
    public void EditNameWithUniqueValue() {
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

    @Test
    public void EditNameWithEmptyValue() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().goToDictionaryPage(dictionaryData);
        app.getDictionaryHelper().editDictionaryName(dictionaryData, "");

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Collections.sort(dictionaryDataList1);
        Collections.sort(dictionaryDataList2);

        assertEquals(dictionaryDataList1, dictionaryDataList2, "Editing name with empty value");

    }

    @Test
    public void EditNameWithExistingValue() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().editDictionaryName(dictionaryData, dictionaryData.getName());

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        //assertEquals(editingResult,  "Dictionary " + dictionaryData.getName() + " already exist");

        Collections.sort(dictionaryDataList1);
        Collections.sort(dictionaryDataList2);

        assertEquals(dictionaryDataList1, dictionaryDataList2, "Editing name with existing value");
    }

    @Test
    public void EditNameWithLongValue() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        String newLongName = RandomStringUtils.randomAlphanumeric(1000);
        app.getDictionaryHelper().editDictionaryName(dictionaryData, newLongName);

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getName(),
                newLongName,
                "Editing with unique long name");
    }

    @Test
    public void EditDescriptionWithLongValue() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        String newLongDescription = RandomStringUtils.randomAlphanumeric(10000);
        app.getDictionaryHelper().editDictionaryDescription(dictionaryData, newLongDescription);

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getDescription(),
                newLongDescription,
                "Editing with unique long description");
    }

    @Test
    public void EditDescriptionWithUniqueValue() {
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
        app.getDictionaryHelper().editDictionaryDescription(dictionaryData, "new" + dictionaryData.getDescription());

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getDescription(),
                "new" + dictionaryData.getDescription(),
                "Editing with unique description");
    }

    @Test
    public void EditNameWithSpaces() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        app.getNavigationHelper().goToDictionariesPage();

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().editDictionaryName(dictionaryData, "       ");

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Collections.sort(dictionaryDataList1);
        Collections.sort(dictionaryDataList2);

        assertEquals(dictionaryDataList1, dictionaryDataList2, "Editing name with spaces");
    }

    @Test
    public void EditNameWithValueContainingCoupleOfSpaces() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        app.getDictionaryHelper().editDictionaryName(dictionaryData, "contains couple of spaces  " + dictionaryData.getName());

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getName(),
                "contains couple of spaces  " + dictionaryData.getName(),
                "Editing name with value containing couple of spaces");
    }


    @Test
    public void EditDescriptionWithSpaces() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);

        app.getNavigationHelper().goToDictionariesPage();

        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getNavigationHelper().goToDictionariesPage();

        String spacesDescription = "       ";
        app.getDictionaryHelper().editDictionaryDescription(dictionaryData, spacesDescription);

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        Set<DictionaryData> dictionaryDataSet1 = new HashSet<>(dictionaryDataList1);
        Set<DictionaryData> dictionaryDataSet2 = new HashSet<>(dictionaryDataList2);

        dictionaryDataSet2.removeAll(dictionaryDataSet1);

        assertEquals(dictionaryDataSet2.iterator().next().getDescription(),
                spacesDescription,
                "Editing description with spaces");
    }

}
