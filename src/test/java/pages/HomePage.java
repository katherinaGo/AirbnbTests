package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HomePage extends BasePage {

    private static final String LANGUAGE_CURRENCY_PATH = "//div[@class='_iglww7']//div[@class='_z5mecy']";
    private static final String LANGUAGE_BTN_PATH = "//div[@class='_ojs7nk']";
    private static final String LANGUAGE_FORM_PATH = "//li[@class='_1fy05hv']";
    private static final String RUSSIAN_BTN_PATH = "//div[contains(text(), 'Русский')]";
    private static final String LOCATION_INPUT_PATH = "//input[@class='_1xq16jy']";
    private static final String SUBMIT_BTN_PATH = "//span[@class='_m9v25n']";
    private static final By FOUND_RESULT_TITLE_CLASS = By.className("_14i3z6h");

    private static final String RUSSIAN_URL = "https://www.airbnb.ru/";


    public HomePage openLanguageMenu() {
        clickLanguageCurrencyBtn();
        clickLanguageOption();
        return this;
    }

    public HomePage chooseRussianLanguage() {
        waitForNumberOfElementToBeByXpath(LANGUAGE_FORM_PATH, 91);
        $(By.xpath(RUSSIAN_BTN_PATH)).click();
        return this;
    }

    public HomePage isRussianSelected() {
        String currentUrl = url();
        boolean isLanguageChanged = currentUrl.contains(RUSSIAN_URL);
        assertTrue(isLanguageChanged, "Language wasn't changed.");
        return this;
    }

    public HomePage searchPlacesByLocations(String location) {
        inputLocation(location);
        clickSearchBtn();
        return this;
    }

    public void checkIfCorrectPlaceDisplayed(String location) {
        waitForElementVisibleByID_NAME_CSS(FOUND_RESULT_TITLE_CLASS);
        String expectedFoundResult = $(FOUND_RESULT_TITLE_CLASS).getAttribute("innerText");
        boolean isCorrectLocationDisplayed = expectedFoundResult.contains(location);
        assertTrue(isCorrectLocationDisplayed, "Places found not in correspond location: " + location);
    }

    public void checkIfLocationsNotDisplayed(String invalidLocation) {
        boolean noInvalidLocationFound = $(FOUND_RESULT_TITLE_CLASS).isDisplayed();
        assertFalse(noInvalidLocationFound, "Places found: " + invalidLocation);
    }

    private void clickLanguageCurrencyBtn() {
        $(By.xpath(LANGUAGE_CURRENCY_PATH)).click();
    }

    private void clickLanguageOption() {
        $(By.xpath(LANGUAGE_BTN_PATH)).click();
    }

    private void inputLocation(String location) {
        waitForElementVisibleByXpath(LOCATION_INPUT_PATH);
        $(By.xpath(LOCATION_INPUT_PATH)).sendKeys(location);
    }

    private void clickSearchBtn() {
        $(By.xpath(SUBMIT_BTN_PATH)).click();
    }
}