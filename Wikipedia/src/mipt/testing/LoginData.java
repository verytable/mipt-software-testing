package mipt.testing;

/**
 * Created by arseny on 08.10.14.
 */
public class LoginData {

    private String login;
    private String pass;
    private String expectedResult;

    public String getLogin() {
        return login;
    }

    public LoginData setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public LoginData setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public LoginData setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    public LoginData() {
        login = "";
        pass = "";
        expectedResult = "";
    }

    @Override
    public String toString() {
        return "LoginData [login = " + login + " password = " + pass + "expected result = " + getExpectedResult();
    }
}
