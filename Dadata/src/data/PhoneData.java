package data;

/**
 * Created by arseny on 05.11.14.
 */
public class PhoneData {

    private String phoneNumber;
    private String expectedResult;

    public PhoneData() {
        phoneNumber = "";
        expectedResult = "";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneData setPhoneData(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public PhoneData setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    @Override
    public String toString() {
        return "PhoneData [phoneNumber = " + phoneNumber + ", expectedResult = " + expectedResult + "]";
    }
}
