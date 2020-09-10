package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage extends BasePage {
    private static final String URL = "https://www.airbnb.com/";
    private static final String MAIN_NAVIGATION_MENU_PATH = "//div[@aria-label='Main navigation menu']";
    private static final String LOGIN_FORM_PATH = "//div[contains(text(), 'Log in')]";
    private static final String CONTINUE_WITH_EMAIL_OPTION_PATH = "//div[contains(text(), 'Continue with email')]";
    private static final By EMAIL_FIELD_NAME = By.name("email");
    private static final By PASSWORD_FIELD_NAME = By.name("password");
    private static final String LOG_IN_BTN_PATH = "//span[contains(text(), 'Log in')]";
    private static final By MENU_BUTTON_WHEN_LOGGED_IN_CSS = By.cssSelector("._1wzp0xs");
    private static final String ACCOUNT_TITLE_PATH = "//div[contains(text(), 'Account')]";
    private static final String ERROR_PATH = "//div[@class='_1yhfti2']";
    private static final String EMAIL_ERROR_PATH = "//*[@id='email-login-email__error']";
    private static final String PASSWORD_ERROR_PATH = "//*[@id='email-login-password__error']";

    public LoginPage openPage() {
        open(URL);
        waitForElementVisibleByID_NAME_CSS(By.className("_1cb9q3xq"));
        return this;
    }

    public LoginPage loginWithValidData(String email, String password) {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        inputEmail(email);
        inputPassword(password);
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
        return this;
    }

    public void checkIfLoggedInSuccessfully() {
        clickMenuProfileButton();
        waitForElementVisibleByXpath(ACCOUNT_TITLE_PATH);
        boolean isLoggedIn = $(By.xpath(ACCOUNT_TITLE_PATH)).isDisplayed();
        assertTrue(isLoggedIn, "User not logged in");
    }

    public LoginPage loginWithInvalidData(String email, String password) {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        inputEmail(email);
        inputPassword(password);
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
        return this;
    }

    private String getErrorMessageWhenInvalidEmail() {
        waitForElementVisibleByXpath(EMAIL_ERROR_PATH);
        return getTextFromElementXPath(EMAIL_ERROR_PATH);
    }

    public void validateErrorWhenLoginWithInvalidEmail(String expectedError) {
        String actualError = getErrorMessageWhenInvalidEmail();
        boolean isErrorCorrect = expectedError.equals(actualError);
        assertTrue(isErrorCorrect, "Incorrect error is displayed when input invalid email.");
    }

    public LoginPage loginWithNoData() {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
        waitForNumberOfElementToBeByXpath(ERROR_PATH, 2);
        return this;
    }

    public LoginPage checkIfFieldsRequired(String expectedEmail, String expectedPassword) {
        String actualResultErrorEmail = getTextFromElementXPath(EMAIL_ERROR_PATH);
        String actualResultErrorPassword = getTextFromElementXPath(PASSWORD_ERROR_PATH);
        assertEquals(actualResultErrorEmail, expectedEmail, "Error that email required is not displayed.");
        assertEquals(actualResultErrorPassword, expectedPassword, "Error that password required is not displayed.");
        return this;
    }

    private void clickOnMenuButton() {
        waitForElementVisibleByXpath(MAIN_NAVIGATION_MENU_PATH);
        $(By.xpath(MAIN_NAVIGATION_MENU_PATH)).click();
    }

    private void clickOnLoginBtn() {
        waitForElementVisibleByXpath(LOGIN_FORM_PATH);
        $(By.xpath(LOGIN_FORM_PATH)).click();
    }

    private void clickContinueWithEmailOption() {
        waitForElementVisibleByXpath(CONTINUE_WITH_EMAIL_OPTION_PATH);
        $(By.xpath(CONTINUE_WITH_EMAIL_OPTION_PATH)).click();
    }

    private void inputEmail(String email) {
        $(EMAIL_FIELD_NAME).sendKeys(email);
    }

    private void inputPassword(String password) {
        $(PASSWORD_FIELD_NAME).sendKeys(password);
        waitForElementVisibleByXpath(LOG_IN_BTN_PATH);
    }

    private void clickMenuProfileButton() {
        waitForElementVisibleByID_NAME_CSS(MENU_BUTTON_WHEN_LOGGED_IN_CSS);
        $(MENU_BUTTON_WHEN_LOGGED_IN_CSS).click();
    }
}