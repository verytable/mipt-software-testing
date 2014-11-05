package mipt.testing;

/**
 * Created by arseny on 08.10.14.
 */
public class SignupData {

    private String login;
    private String pass;
    private String retype;
    private String expectedResult;

    public String getLogin() {
        return login;
    }

    public SignupData setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public SignupData setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getRetype() {
        return retype;
    }

    public SignupData setRetype(String retype) {
        this.retype = retype;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public SignupData setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    public SignupData() {
        login = "";
        pass = "";
        retype = "";
        expectedResult = "";
    }

    @Override
    public String toString() {
        return "SignupData [login=" + login + ", password=" + pass + ", retyped=" + retype + ", expected result=" + getExpectedResult();
    }
}
