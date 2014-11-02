package framework;

import data.GroupData;
import org.openqa.selenium.By;

/**
 * Created by arseny on 22.10.14.
 */
public class GroupHelper extends BaseHelper {

    public GroupHelper(AppManager manager) {
        super(manager);
    }

    public String createGroup(GroupData groupData) {
        scrollAndClick(By.cssSelector(locators.getProperty("addGroupButton")));
        type(By.id(locators.getProperty("addGroupNameField")), groupData.getName());
        type(By.id(locators.getProperty("addGroupDescriptionField")), groupData.getDescription());
        scrollAndClick(By.id(locators.getProperty("addGroupProceedButton")));

        return getText(By.id(locators.getProperty("addGroupErrorField")));
    }
}
