package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final int waitTimeInSeconds;

    private final By inputEmailLocator = By.xpath("//input[@id='E-mail']");
    private final By inputPasswordLocator = By.xpath("//input[@name='password']");
    private final By buttonEnterLocator = By.xpath("//button[.//text()='Продолжить']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitTimeInSeconds = 5;
    }

    public void login(String email, String password) {
        WaitUtils.waitForElementVisibility(driver, inputEmailLocator, waitTimeInSeconds);

        driver.findElement(inputEmailLocator).sendKeys(email);
        driver.findElement(inputPasswordLocator).sendKeys(password);

        driver.findElement(buttonEnterLocator).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //public boolean isErrorMessageDisplayed() {
//
    //}
}
