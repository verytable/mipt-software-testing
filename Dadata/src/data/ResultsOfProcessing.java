package data;

/**
 * Created by arseny on 05.11.14.
 */
public class ResultsOfProcessing {

    private String initialValue;
    private String phoneNumberType;
    private String operator;
    private String region;
    private String timeZone;

    public ResultsOfProcessing() {
        this.initialValue = "";
        this.phoneNumberType = "";
        this.operator = "";
        this.region = "";
        this.timeZone = "";
    }

    public String getInitialValue() {
        return initialValue;
    }

    public ResultsOfProcessing setInitialValue(String initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    public String getPhoneNumberType() {
        return phoneNumberType;
    }

    public ResultsOfProcessing setPhoneNumberType(String phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public ResultsOfProcessing setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public ResultsOfProcessing setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public ResultsOfProcessing setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsOfProcessing that = (ResultsOfProcessing) o;

        if (!initialValue.equals(that.initialValue)) return false;
        if (!operator.equals(that.operator)) return false;
        if (!phoneNumberType.equals(that.phoneNumberType)) return false;
        if (!region.equals(that.region)) return false;
        if (!timeZone.equals(that.timeZone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = initialValue.hashCode();
        result = 31 * result + phoneNumberType.hashCode();
        result = 31 * result + operator.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + timeZone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResultsOfProcessing{" +
                "initialValue='" + initialValue + '\'' +
                ", phoneNumberType='" + phoneNumberType + '\'' +
                ", operator='" + operator + '\'' +
                ", region='" + region + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
