package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

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

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void loginWithValidData(String email, String password) {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        inputEmail(email);
        inputPassword(password);
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
    }

    public boolean checkIfLoggedInSuccessfully() {
        clickMenuProfileButton();
        waitForElementVisibleByXpath(ACCOUNT_TITLE_PATH);
        return driver.findElement(By.xpath(ACCOUNT_TITLE_PATH)).isDisplayed();
    }

    public void loginWithInvalidData(String email, String password) {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        inputEmail(email);
        inputPassword(password);
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
    }

    public String getErrorMessageWhenInvalidEmail() {
        waitForElementVisibleByXpath(EMAIL_ERROR_PATH);
        return getTextFromElementXPath(EMAIL_ERROR_PATH);
    }

    public void loginWithNoData() {
        clickOnMenuButton();
        clickOnLoginBtn();
        clickContinueWithEmailOption();
        pressEnterToSubmitData(LOG_IN_BTN_PATH);
        waitForNumberOfElementToBeByXpath(ERROR_PATH, 2);
    }

    public void checkFieldsRequired(String expectedEmail, String expectedPassword) {
        String actualResultErrorEmail = getTextFromElementXPath(EMAIL_ERROR_PATH);
        String actualResultErrorPassword = getTextFromElementXPath(PASSWORD_ERROR_PATH);
        assertEquals(actualResultErrorEmail, expectedEmail, "Error that email required is not displayed.");
        assertEquals(actualResultErrorPassword, expectedPassword, "Error that password required is not displayed.");
    }

    private void clickOnMenuButton() {
        waitForElementVisibleByXpath(MAIN_NAVIGATION_MENU_PATH);
        driver.findElement(By.xpath(MAIN_NAVIGATION_MENU_PATH)).click();
    }

    private void clickOnLoginBtn() {
        waitForElementVisibleByXpath(LOGIN_FORM_PATH);
        driver.findElement(By.xpath(LOGIN_FORM_PATH)).click();
    }

    private void clickContinueWithEmailOption() {
        waitForElementVisibleByXpath(CONTINUE_WITH_EMAIL_OPTION_PATH);
        driver.findElement(By.xpath(CONTINUE_WITH_EMAIL_OPTION_PATH)).click();
    }

    private void inputEmail(String email) {
        driver.findElement((EMAIL_FIELD_NAME)).sendKeys(email);
    }

    private void inputPassword(String password) {
        driver.findElement((PASSWORD_FIELD_NAME)).sendKeys(password);
        waitForElementVisibleByXpath(LOG_IN_BTN_PATH);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private void clickMenuProfileButton() {
        waitForElementVisibleByID_NAME_CSS(MENU_BUTTON_WHEN_LOGGED_IN_CSS);
        driver.findElement(MENU_BUTTON_WHEN_LOGGED_IN_CSS).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}