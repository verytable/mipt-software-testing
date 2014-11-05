package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arseny on 05.11.14.
 */
abstract public class CSVDataLoader<T> {
    public List<T> loadDataFromCsvFile(String file) throws IOException {
        List<T> list = new ArrayList<T>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                list.add(generateObject(parts));
                line = reader.readLine();
            }
            reader.close();
            return list;
        }
    }

    protected abstract T generateObject(String[] parts);
}
