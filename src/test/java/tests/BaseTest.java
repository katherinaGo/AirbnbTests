package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected LoginPage loginPage;
    protected ProfilePage profilePage;
    protected HomePage homePage;

    @BeforeMethod
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("intl.accept_languages", "en-USA");
//        FirefoxOptions options = new FirefoxOptions();
//        options.setProfile(profile);
//        driver = new FirefoxDriver(options);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
