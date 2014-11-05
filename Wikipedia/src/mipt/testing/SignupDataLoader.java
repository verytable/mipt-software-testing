package mipt.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arseny on 08.10.14.
 */
public class SignupDataLoader {
    public static List<SignupData> loadDataFromCsvFile(File file) throws IOException {
        List<SignupData> list = new ArrayList<SignupData>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(";");
            SignupData signupData = new SignupData()
                    .setLogin(part[0])
                    .setPass(part[1])
                    .setRetype(part[2])
                    .setExpectedResult(part[3]);
            list.add(signupData);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return list;
    }
}
