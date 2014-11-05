package tests;

import data.PhoneData;
import framework.AppManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by arseny on 05.11.14.
 */
public class TestBase {

    public AppManager app;

    @BeforeSuite
    public void setUp() throws Exception {
        Properties properties = new Properties();
        String configFile = "test.properties";
        properties.load(new FileReader(new File(configFile)));
        app = new AppManager(properties);
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    public static Iterator<Object[]> wrapPhoneNumberForDataProvider(List<PhoneData> phoneNumbers) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (PhoneData phoneData: phoneNumbers) {
            list.add(new Object[]{phoneData});
        }
        return list.iterator();
    }
}
