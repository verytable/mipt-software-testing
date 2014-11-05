package data;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneDataLoader extends CSVDataLoader<PhoneData> {
    @Override
    protected PhoneData generateObject(String[] parts) {
        return new PhoneData().setPhoneData(parts[0]).setExpectedResult(parts[1]);
    }
}
