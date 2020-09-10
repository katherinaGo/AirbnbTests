package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProfilePage extends BasePage {

    private static final String MAIN_NAVIGATION_MENU_PATH = "//button[@class='_1usxwsg6']";
    private static final String ACCOUNT_MENU_PATH = "//div[contains(text(), 'Account')]";
    private static final By TITLE_PROFILE_NAME_CSS = By.cssSelector("._1p3joamp");
    private static final By GO_TO_PROFILE_CSS = By.cssSelector("._ba3mo2p");
    private static final String UPDATE_PHOTO_PATH = "//div[@class='_pdgm0p']//a[@class='_njl8u63']";
    private static final By UPLOAD_FILE_FROM_COMP_NAME = By.id("profile_pic_upload");
    private static final By UPLOAD_FILE_FROM_COMP_CSS = By.cssSelector(".btn-large");
    private static final String PATH_TO_FILE = "/Users/kate/Documents/AirbnbTests/src/test/resources/profilePhoto.jpg";
    private static final String DELETE_PHOTO_BTN_NAME = "//button[@class='picture-tile-delete overlay-btn']";

    public ProfilePage openProfile() {
        clickOnMenuButton();
        clickAccountFormBtn();
        clickGoTOProfileBtn();
        return this;
    }

    public ProfilePage uploadFileFromComp() {
        clickUpdatePhotoBtn();
        $(By.xpath("//input[@type='file']")).sendKeys(PATH_TO_FILE);
        return this;
    }

    public void checkIfPhotoLoaded() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        boolean isPhotoLoaded = $(By.xpath(DELETE_PHOTO_BTN_NAME)).isDisplayed();
        assertTrue(isPhotoLoaded, "Photo is not loaded");
    }

    public ProfilePage deletePhotoFromProfile() {
        clickUpdatePhotoBtn();
        clickDeletePhotoBtn();
        return this;
    }

    public void checkIfPhotoDeleted() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        boolean isPhotoDeleted = $(By.xpath(DELETE_PHOTO_BTN_NAME)).isDisplayed();
        assertFalse(isPhotoDeleted, "Photo is not deleted");
    }

    private void clickAccountFormBtn() {
        waitForElementVisibleByXpath(ACCOUNT_MENU_PATH);
        $(By.xpath(ACCOUNT_MENU_PATH)).click();
        waitForElementVisibleByID_NAME_CSS(TITLE_PROFILE_NAME_CSS);
    }

    private void clickOnMenuButton() {
        waitForElementVisibleByXpath(MAIN_NAVIGATION_MENU_PATH);
        $(By.xpath(MAIN_NAVIGATION_MENU_PATH)).click();

    }

    private void clickGoTOProfileBtn() {
        waitForElementVisibleByID_NAME_CSS(GO_TO_PROFILE_CSS);
        $(GO_TO_PROFILE_CSS).click();
        waitForElementVisibleByXpath(UPDATE_PHOTO_PATH);
    }

    private void clickUpdatePhotoBtn() {
        $(By.xpath(UPDATE_PHOTO_PATH)).click();
        waitForElementVisibleByID_NAME_CSS(UPLOAD_FILE_FROM_COMP_NAME);
    }

    private void clickDeletePhotoBtn() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        $(By.xpath(DELETE_PHOTO_BTN_NAME)).click();
        pressEnterToSubmitInAlert();
    }
}