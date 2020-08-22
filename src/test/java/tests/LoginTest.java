package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static final String EMAIL_VALUE = "kateg@yopmail.com";
    private static final String PASSWORD_VALUE = "testPass1";

    @DataProvider(name = "invalidData")
    public Object[][] invalidEmails() {
        return new Object[][]{{"qqq"}, {"test7@.com"}};
    }

    @Test
    public void loginWithValidDataTest() {
        loginPage.openPage();
        loginPage.loginWithValidData(EMAIL_VALUE, PASSWORD_VALUE);
        boolean isLoggedIn = loginPage.checkIfLoggedInSuccessfully();
        assertTrue(isLoggedIn, "User not logged in");
    }

    @Test(dataProvider = "invalidData")
    public void loginWithInvalidDataTest(String invalidEmail) {
        loginPage.openPage();
        loginPage.loginWithInvalidData(invalidEmail, PASSWORD_VALUE);
        String expectedResult = "Enter a valid email.";
        String actualResult = loginPage.getErrorMessageWhenInvalidEmail();
        assertEquals(expectedResult, actualResult, "Incorrect error is displayed when input invalid email.");
    }

    @Test
    public void fieldsRequiredTest() {
        loginPage.openPage();
        loginPage.loginWithNoData();
        String expectedResultEmail = "Email is required.";
        String expectedResultPassword = "Password is required.";
        loginPage.checkFieldsRequired(expectedResultEmail, expectedResultPassword);
    }

}