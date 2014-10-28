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
        click(By.cssSelector("button.btn.flat"));
        type(By.id("groups-add-input-name"), groupData.getName());
        type(By.id("groups-add-input-description"), groupData.getDescription());
        click(By.id("groups-add-button-submit"));

        // TODO: check that returns error message or empty string
        return getText(By.id("groups-add-help-name"));
    }
}
