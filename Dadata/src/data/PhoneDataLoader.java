package data;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneDataLoader extends CSVDataLoader<PhoneData> {
    @Override
    protected PhoneData generateObject(String[] parts) {

        ResultsOfProcessing expectedResult =
                new ResultsOfProcessing()
                        .setInitialValue(parts[0])
                        .setPhoneNumberType(parts[1])
                        .setOperator(parts[2])
                        .setRegion(parts[3])
                        .setTimeZone(parts[4]);

        return new PhoneData()
                .setPhoneData(expectedResult.getInitialValue())
                .setExpectedResult(expectedResult);
    }
}
