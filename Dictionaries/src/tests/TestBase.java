package tests;

import data.DictionaryData;
import data.LoginData;
import framework.AppManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

/**
 * Created by arseny on 22.10.14.
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

    public static List<Object[]> wrapLoginForDataProvider(List<LoginData> loginsInput) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (LoginData login: loginsInput) {
            list.add(new Object[]{login});
        }
        return list;
    }

    public void AddDictionary(DictionaryData dictionaryData, String testDescription) {
        List<DictionaryData> dictionaryDataList1 = app.getDictionaryHelper().getDictionaries();

        app.getDictionaryHelper().createDictionary(dictionaryData);

        List<DictionaryData> dictionaryDataList2 = app.getDictionaryHelper().getDictionaries();

        if (!dictionaryDataList1.contains(dictionaryData)) {
            dictionaryDataList1.add(dictionaryData);
        }

        Collections.sort(dictionaryDataList1);
        Collections.sort(dictionaryDataList2);

        assertEquals(dictionaryDataList1, dictionaryDataList2, testDescription);
    }
}
