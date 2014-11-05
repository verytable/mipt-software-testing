package data;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneData {

    private String phoneNumber;
    private ResultsOfProcessing expectedResult;

    public PhoneData() {
        phoneNumber = "";
        expectedResult = new ResultsOfProcessing();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneData setPhoneData(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ResultsOfProcessing getExpectedResult() {
        return expectedResult;
    }

    public PhoneData setExpectedResult(ResultsOfProcessing expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    @Override
    public String toString() {
        return "PhoneData [phoneNumber = " + phoneNumber + ", expectedResult = " + expectedResult + "]";
    }
}
