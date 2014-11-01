package tests;

import data.DictionaryData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

import static org.testng.Assert.assertEquals;


/**
 * Created by arseny on 22.10.14.
 */
public class DictionaryCreationTests extends TestBase {

    @Test
    public void AddNewDictionary() {
//        app.getNavigationHelper().goToDictionariesPage();
//        app.getDictionaryHelper().createSomeDictionaries(20);

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(20))
                .setDescription(RandomStringUtils.randomAlphanumeric(50))
                .setExpectedResult("OK");

        AddDictionary(dictionaryData, "Create usual dictionary");
    }

    @Test
    public void AddExistingDictionary() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.random(20))
                .setDescription(RandomStringUtils.random(50))
                .setExpectedResult("OK");

        app.getDictionaryHelper().createDictionary(dictionaryData);
        app.getNavigationHelper().goToDictionariesPage();
        String creationResult = app.getDictionaryHelper().createDictionary(dictionaryData);

        assertEquals(creationResult, "Dictionary " + dictionaryData.getName() + " already exist",
                "Create dictionary with existing name and description");
    }

    @Test
    public void AddDictionaryWithLongName() {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(10000))
                .setDescription(RandomStringUtils.randomAlphanumeric(100))
                .setExpectedResult("OK");

        AddDictionary(dictionaryData, "Create dictionary with long name");
    }

    @Test
    public void AddDictionaryWithLongDescription() {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName(RandomStringUtils.randomAlphanumeric(100))
                .setDescription(RandomStringUtils.randomAlphanumeric(10000))
                .setExpectedResult("OK");

        AddDictionary(dictionaryData, "Create dictionary with long description");
    }

    @Test
    public void AddDictionaryWithSpacesName() {
        app.getNavigationHelper().goToDictionariesPage();

        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName("    ")
                .setDescription(RandomStringUtils.randomAlphanumeric(100))
                .setExpectedResult("OK");

        String creationResult = app.getDictionaryHelper().createDictionary(dictionaryData);

        assertEquals(creationResult, "Field is required", "Create dictionary with name consisting of spaces");
    }

    @Test
    public void AddDictionaryWithCoupleOfSpacesInName() {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName("firstName  " + RandomStringUtils.randomAlphanumeric(10))
                .setDescription(RandomStringUtils.randomAlphanumeric(100))
                .setExpectedResult("OK");

        AddDictionary(dictionaryData, "Create dictionary with name containing 2 spaces in a row");
    }

    @Test
    public void AddDictionaryWithLineBreak() {
        DictionaryData dictionaryData = new DictionaryData();
        dictionaryData.setName("name contains line break\n" + RandomStringUtils.randomAlphanumeric(10))
                .setDescription(RandomStringUtils.randomAlphanumeric(100))
                .setExpectedResult("OK");

        AddDictionary(dictionaryData, "Create dictionary with name containing line break");
    }

}
