package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;



public class CucumberSelenium {
    private static final String testEmail = "1234Aa@askd.com";
    private static final String testPassword = "1234Aa!!";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://test-mrn.astondevs.ru");
        WaitUtils.waitForPageLoad(driver, 5);

        By buttonLKLocator = By.xpath("//a[.//text()='Войти']");
        WaitUtils.waitForElementVisibility(driver, buttonLKLocator, 10);
        WebElement buttonLK = driver.findElement(buttonLKLocator);
        buttonLK.click();

        WaitUtils.waitForPageLoad(driver, 5);

        By inputEmailLocator = By.xpath("//input[@id='E-mail']");
        WebElement inputEmailField = driver.findElement(inputEmailLocator);
        inputEmailField.sendKeys(testEmail);

        By inputPasswordLocator = By.xpath("//input[@name='password']");
        WebElement inputPasswordField = driver.findElement(inputPasswordLocator);
        inputPasswordField.sendKeys(testPassword);

        By buttonEnterLocator = By.xpath("//button[.//text()='Продолжить']");
        WebElement buttonEnter = driver.findElement(buttonEnterLocator);
        buttonEnter.click();

        WaitUtils.waitForPageLoad(driver, 5);

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://test-mrn.astondevs.ru";
        softAssert.assertEquals(actualUrl, expectedUrl, "После входа URL должен вести на главную.");

        softAssert.assertAll();
        driver.quit();
    }
}
