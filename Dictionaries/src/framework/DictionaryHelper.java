package framework;

import data.DictionaryData;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.assertEquals;

/**
 * Created by arseny on 23.10.14.
 */
public class DictionaryHelper extends BaseHelper {

    public DictionaryHelper(AppManager manager) {
        super(manager);
    }

    public List<DictionaryData> getDictionaries() {
        manager.getNavigationHelper().goToDictionariesPage();

        ArrayList<DictionaryData> dictionaries = new ArrayList<>();

        int numberOfPages = getNumberOfPages();

        for (int page = 1; page <= numberOfPages; ++page) {
            dictionaries.addAll(listDictionaries());
            if (isElementPresent(By.className("next"))) {
                click(By.className("next"));
            }
        }

        return dictionaries;
    }

    public List<DictionaryData> listDictionaries() {
        try {
            List<WebElement> elements = manager.wait.until(visibilityOfElementsLocated(By.xpath(locators.getProperty("dictionariesTableRows"))));

            List<DictionaryData> dictionaryDataList = new ArrayList<>();
            if (!(elements.size() == 1 && elements.get(0).getText().equals("No dictionaries found"))) {
                for (WebElement element : elements) {
                    DictionaryData dictionaryData = new DictionaryData();
                    dictionaryDataList.add(dictionaryData
                            .setName(element.findElement(By.className(locators.getProperty("dictionaryNameColumn"))).getText())
                            .setDescription(element.findElement(By.cssSelector(locators.getProperty("dictionaryDescriptionColumn"))).getText()));
                }
            }
            return dictionaryDataList;
        } catch (StaleElementReferenceException ex) {
            return listDictionaries();
        }
    }

    public String createDictionary(DictionaryData dictionaryData) {
        manager.wait.until(visibilityOfElementLocated(By.xpath(locators.getProperty("addDictionaryButton"))));
        click(By.xpath(locators.getProperty("addDictionaryButton")));

        type(By.id(locators.getProperty("dictionaryAddNameField")), dictionaryData.getName());
        type(By.id(locators.getProperty("dictionaryAddDescriptionField")), dictionaryData.getDescription());

        manager.wait.until(visibilityOfElementLocated(By.id(locators.getProperty("createDictionaryButton"))));
        click(By.id("dictionaries-add-button-submit"));

        if (isElementPresent(By.id(locators.getProperty("createDictionaryButton")))) {
            manager.wait.until(visibilityOfElementLocated(By.className(locators.getProperty("addDictionaryErrorField"))));
        }

        return getText(By.className(locators.getProperty("addDictionaryErrorField")));
    }

    public void createSomeDictionaries(int numberOfDictionaries) {
        DictionaryData dictionaryData = new DictionaryData();
        for (int i = 0; i < numberOfDictionaries; ++i) {
            dictionaryData.setName(RandomStringUtils.random(20))
                    .setDescription(RandomStringUtils.random(50))
                    .setExpectedResult("OK");
            createDictionary(dictionaryData);
            manager.getNavigationHelper().goToDictionariesPage();
        }
    }

    public String removeDictionaryByDictionaryData(DictionaryData dictionaryData) {
        int page = goToDictionaryPage(dictionaryData);

        List<DictionaryData> dictionaryDataList = listDictionaries();

        int dictionaryRow = dictionaryDataList.indexOf(dictionaryData) + 1;

        removeNthTopDictionary(dictionaryRow);
        return "";
    }

    public void removeNthTopDictionary(int n) {
        String deletingElementXPath = String.format(locators.getProperty("deletingDictionaryRow"), n);
        try {
            WebElement deletingElement = manager.wait.until(visibilityOfElementLocated(By.xpath(deletingElementXPath)));
            Actions action = new Actions(driver);
            action.moveToElement(deletingElement).perform();
            click(By.xpath(String.format(locators.getProperty("deletingDictionaryXSymbol"), n)));
            click(By.className(locators.getProperty("deletingDictionaryConfirmationButton")));
        } catch (StaleElementReferenceException ex) {
            removeNthTopDictionary(n);
        }
    }

    public void removeSomeDictionaries(int numberOfDictionaries) {
        for (int i = 0; i < numberOfDictionaries; ++i) {
            manager.getDictionaryHelper().removeNthTopDictionary(1);
            manager.getNavigationHelper().goToDictionariesPage();
        }
    }

    public int getNumberOfPages() {
        Boolean isMoreThanOnePage = isElementPresent(By.className(locators.getProperty("pagingsClass")));

        if (isMoreThanOnePage) {
            List<WebElement> pagings = driver.findElement(By.className(locators.getProperty("pagingsClass")))
                    .findElements(By.xpath(locators.getProperty("pagings")));

            if (pagings.size() < 2) {
                return 1;
            }
            return Integer.parseInt(pagings.get(pagings.size() - 2).getText());
        } else {
            return 1;
        }
    }

    public int goToDictionaryPage(DictionaryData dictionaryData) {
        int numberOfPages = getNumberOfPages();
        for (int page = 1; page < numberOfPages; ++page) {
            List<DictionaryData> dictionaryDataList = listDictionaries();
            if (dictionaryDataList.contains(dictionaryData)) {
                return page;
            }
            if (isElementPresent(By.className(locators.getProperty("nextButton")))) {
                click(By.className(locators.getProperty("nextButton")));
            }
        }
        return -1;
    }

    public String editDictionaryName(DictionaryData dictionaryData, String newName) {
        int page = goToDictionaryPage(dictionaryData);

        List<DictionaryData> dictionaryDataList = listDictionaries();

        int dictionaryRow = dictionaryDataList.indexOf(dictionaryData) + 1;

        editNthDictionaryName(dictionaryRow, newName);
        return "";
    }

    public void editNthDictionaryName(int n, String newName) {
        String editingElementXPath = String.format(locators.getProperty("editingDictionaryRow"), n);

        try {
            WebElement editingElement = manager.wait.until(visibilityOfElementLocated(By.xpath(editingElementXPath)));
            Actions action = new Actions(driver);
            action.moveToElement(editingElement).perform();
            click(By.xpath(String.format(locators.getProperty("editingDictionaryPencilSymbol"), n)));

            manager.driver.findElement(By.cssSelector(locators.getProperty("editingDictionaryNameField"))).clear();
            manager.driver.findElement(By.cssSelector(locators.getProperty("editingDictionaryNameField"))).sendKeys(newName);

        } catch (StaleElementReferenceException ex) {
            removeNthTopDictionary(n);
        }
    }

}
