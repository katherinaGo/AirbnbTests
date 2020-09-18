package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import steps.FindLocationSteps;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    protected LoginPage loginPage;
    protected ProfilePage profilePage;
    protected HomePage homePage;
    protected FindLocationSteps locationSteps;

    @BeforeMethod
    public void setUp() {
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.clickViaJs = false;

        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        homePage = new HomePage();
        locationSteps = new FindLocationSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}