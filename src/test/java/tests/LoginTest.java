package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final String EMAIL_VALUE = "kateg@yopmail.com";
    private static final String PASSWORD_VALUE = "testPass1";
    private static final String LOGIN_WITH_INVALID_EMAIL_ERROR = "Enter a valid email.";

    @DataProvider(name = "invalidData")
    public Object[][] invalidEmails() {
        return new Object[][]{{"qqq"}, {"test7@.com"}};
    }

    @Test
    public void loginWithValidDataTest() {
        loginPage.openPage()
                .loginWithValidData(EMAIL_VALUE, PASSWORD_VALUE)
                .checkIfLoggedInSuccessfully();
    }

    @Test(dataProvider = "invalidData")
    public void loginWithInvalidDataTest(String invalidEmail) {
        loginPage.openPage()
                .loginWithInvalidData(invalidEmail, PASSWORD_VALUE)
                .validateErrorWhenLoginWithInvalidEmail(LOGIN_WITH_INVALID_EMAIL_ERROR);
    }

    @Test
    public void fieldsRequiredTest() {
        loginPage.openPage()
                .loginWithNoData();
        String expectedResultEmail = "Email is required.";
        String expectedResultPassword = "Password is required.";
        loginPage.checkIfFieldsRequired(expectedResultEmail, expectedResultPassword);
    }
}