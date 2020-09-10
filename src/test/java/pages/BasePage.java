package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.*;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasePage {

    public void waitForElementVisibleByXpath(String locator) {
        try {
            $(By.xpath(locator)).waitUntil(Condition.visible, 2000);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Element not found, not visible");
        }
    }

    public void waitForElementVisibleByID_NAME_CSS(By locator) {
        try {
            $(locator).waitUntil(Condition.visible, 2000);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Element not found, not visible");
        }
    }

    public void waitForNumberOfElementToBeByID_NAME_Css(By locator, int amountOfElements) {
        try {
            $$(locator).shouldHaveSize(amountOfElements);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Number of element is not correct");
        }
    }

    public void waitForNumberOfElementToBeByXpath(String locator, int amountOfElements) {
        try {
            $$(By.xpath(locator)).shouldHaveSize(amountOfElements);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            Assert.fail("Number of element is not correct");
        }
    }

    public void pressEnterToSubmitData(String locator) {
        WebElement element = $(By.xpath(locator));
        element.submit();
    }

    public String getTextFromElementXPath(String element) {
        return $(By.xpath(element)).getText();
    }

    public void sendTextUsingWebElement(String locator, String text) {
        WebElement element = $(By.xpath(locator));
        element.sendKeys(text);
    }

    public void pressEnterToSubmitInAlert() {
        Alert alert = getWebDriver().switchTo().alert();
        alert.accept();
    }

    public void pressCancelToDismissInAlert() {
        Alert alert = getWebDriver().switchTo().alert();
        alert.dismiss();
    }
}