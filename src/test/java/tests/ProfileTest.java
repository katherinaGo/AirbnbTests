package tests;

import org.testng.annotations.Test;
import utils.RetryAnalyzer;

public class ProfileTest extends BaseTest {
    private static final String EMAIL_VALUE = "kateg@yopmail.com";
    private static final String PASSWORD_VALUE = "testPass1";


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void checkIfPossibleToSetUpProfilePhoto() {
        loginPage.openPage()
                .loginWithValidData(EMAIL_VALUE, PASSWORD_VALUE);
        profilePage.openProfile()
                .uploadFileFromComp()
                .checkIfPhotoLoaded();
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void checkIfPhotoCanBeDeleted() {
        profilePage.openProfile()
                .deletePhotoFromProfile()
                .checkIfPhotoDeleted();
    }
}