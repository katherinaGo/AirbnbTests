package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(String.format("============================ STARTING TEST %s ============================", result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(String.format("============================ FINISHED TEST %s Duration: %ss ============================", result.getName(),
                getExecutionTime(result)));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(String.format("============================ FAILED TEST %s Duration: %ss ============================", result.getName(),
                getExecutionTime(result)));
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(String.format("============================ SKIPPED TEST %s ============================", result.getName()));
    }


    @Attachment(type = "image/png", value = "screenshot")
    public static byte[] takeScreenshot(ITestResult result) {
        try {
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (NoSuchElementException | IllegalStateException ex) {
            return null;
        }
    }

    private long getExecutionTime(ITestResult result) {
        return TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis());
    }
}