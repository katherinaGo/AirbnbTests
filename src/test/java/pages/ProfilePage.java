package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void openProfile() {
        clickOnMenuButton();
        clickAccountFormBtn();
        clickGoTOProfileBtn();
    }

    public void uploadFileFromComp() {
        clickUpdatePhotoBtn();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(PATH_TO_FILE);
//        pressEnterToSubmitInAlert();
    }

    public boolean checkIfPhotoLoaded() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        return driver.findElement(By.xpath(DELETE_PHOTO_BTN_NAME)).isDisplayed();
    }

    public void deletePhotoFromProfile() {
        clickUpdatePhotoBtn();
        clickDeletePhotoBtn();
    }

    public boolean checkIfPhotoDeleted() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        return !driver.findElement(By.xpath(DELETE_PHOTO_BTN_NAME)).isDisplayed();
    }

    private void clickAccountFormBtn() {
        waitForElementVisibleByXpath(ACCOUNT_MENU_PATH);
        driver.findElement(By.xpath(ACCOUNT_MENU_PATH)).click();
        waitForElementVisibleByID_NAME_CSS(TITLE_PROFILE_NAME_CSS);
    }

    private void clickOnMenuButton() {
        waitForElementVisibleByXpath(MAIN_NAVIGATION_MENU_PATH);
        driver.findElement(By.xpath(MAIN_NAVIGATION_MENU_PATH)).click();
    }

    private void clickGoTOProfileBtn() {
        waitForElementVisibleByID_NAME_CSS(GO_TO_PROFILE_CSS);
        driver.findElement(GO_TO_PROFILE_CSS).click();
        waitForElementVisibleByXpath(UPDATE_PHOTO_PATH);
    }

    private void clickUpdatePhotoBtn() {
        driver.findElement(By.xpath(UPDATE_PHOTO_PATH)).click();
        waitForElementVisibleByID_NAME_CSS(UPLOAD_FILE_FROM_COMP_NAME);
    }

    private void clickDeletePhotoBtn() {
        waitForElementVisibleByXpath(DELETE_PHOTO_BTN_NAME);
        driver.findElement(By.xpath(DELETE_PHOTO_BTN_NAME)).click();
        pressEnterToSubmitInAlert();
    }
}