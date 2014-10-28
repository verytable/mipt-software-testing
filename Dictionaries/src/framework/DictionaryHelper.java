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
            List<WebElement> elements = manager.wait.until(visibilityOfElementsLocated(By.xpath("/html/body/section/div/section[1]/table/tbody/tr")));

            List<DictionaryData> dictionaryDataList = new ArrayList<>();
            if (!(elements.size() == 1 && elements.get(0).getText().equals("No dictionaries found"))) {
                for (WebElement element : elements) {
                    DictionaryData dictionaryData = new DictionaryData();
                    dictionaryDataList.add(dictionaryData
                            .setName(element.findElement(By.className("cell-value-inner")).getText())
                            .setDescription(element.findElement(By.cssSelector("span.edit")).getText()));
                }
            }
            return dictionaryDataList;
        } catch (StaleElementReferenceException ex) {
            return listDictionaries();
        }
    }

    public String createDictionary(DictionaryData dictionaryData) {
        manager.wait.until(visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add dictionary')]")));
        click(By.xpath("//button[contains(text(),'Add dictionary')]"));

        type(By.id("dictionaries-add-name"), dictionaryData.getName());
        type(By.id("dictionaries-add-description"), dictionaryData.getDescription());

        manager.wait.until(visibilityOfElementLocated(By.id("dictionaries-add-button-submit")));
        click(By.id("dictionaries-add-button-submit"));

        if (isElementPresent(By.id("dictionaries-add-button-submit"))) {
            manager.wait.until(visibilityOfElementLocated(By.className("help-block")));
        }

        return getText(By.className("help-block"));
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
        String deletingElementXPath = String.format("/html/body/section/div/section[1]/table/tbody/tr[%d]/td[1]", n);
        try {
            WebElement deletingElement = manager.wait.until(visibilityOfElementLocated(By.xpath(deletingElementXPath)));
            Actions action = new Actions(driver);
            action.moveToElement(deletingElement).perform();
            click(By.xpath(deletingElementXPath + "/span/i"));
            click(By.className("btn-primary"));
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
        Boolean isMoreThanOnePage = isElementPresent(By.className("paginator"));

        if (isMoreThanOnePage) {
            List<WebElement> pagings = driver.findElement(By.className("paginator"))
                    .findElements(By.xpath("/html/body/section/div/section[2]/div/ul/li"));

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
            if (isElementPresent(By.className("next"))) {
                click(By.className("next"));
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
        String editingElementXPath = String.format("/html/body/section/div/section[1]/table/tbody/tr[%d]/td[2]", n);

        try {
            WebElement editingElement = manager.wait.until(visibilityOfElementLocated(By.xpath(editingElementXPath)));
            Actions action = new Actions(driver);
            action.moveToElement(editingElement).perform();
            click(By.xpath(editingElementXPath + "/div/span/i"));

            action.moveToElement(editingElement).click().perform();
            action.moveToElement(editingElement).click().sendKeys("HELLO");


            System.err.println(driver.findElement(By.cssSelector("div.cell-value.iconed")));

        } catch (StaleElementReferenceException ex) {
            removeNthTopDictionary(n);
        }
    }

}
