package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    public void waitForElementVisibleByXpath(String locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Element not found, not visible");
        }
    }

    public void waitForElementVisibleByID_NAME_Css(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Element not found, not visible");
        }
    }

    public void waitForNumberOfElementToBeByID_NAME_Css(By locator, int amountOfElements) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, amountOfElements));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Number of element is not correct");
        }
    }

    public void waitForNumberOfElementToBeByXpath(String locator, int amountOfElements) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(locator), amountOfElements));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Number of element is not correct");
        }
    }

    public void pressEnterToSubmitData(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.submit();
    }

    public String getTextFromElementXPath(String element) {
        return driver.findElement(By.xpath(element)).getText();
    }

    public void sendTextUsingWebElement(String locator, String text) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(text);
    }
}