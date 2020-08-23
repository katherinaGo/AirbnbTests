package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProfileTest extends BaseTest {
    private static final String EMAIL_VALUE = "kateg@yopmail.com";
    private static final String PASSWORD_VALUE = "testPass1";


    @Test(priority = 1)
    public void checkIfPossibleToSetUpProfilePhoto() {
        loginPage.openPage();
        loginPage.loginWithValidData(EMAIL_VALUE, PASSWORD_VALUE);
        profilePage.openProfile();
        profilePage.uploadFileFromComp();
        boolean isPhotoLoaded = profilePage.checkIfPhotoLoaded();
        assertTrue(isPhotoLoaded, "Photo is not loaded");
    }

    @Test(priority = 2)
    public void checkIfPhotoCanBeDeleted() {
        profilePage.openProfile();
        profilePage.deletePhotoFromProfile();
        boolean isPhotoDeleted = profilePage.checkIfPhotoDeleted();
        assertTrue(isPhotoDeleted, "Photo is not deleted");
    }
}