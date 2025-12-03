package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inputEmailLocator = By.xpath("//input[@id='E-mail']");
    private final By inputPasswordLocator = By.xpath("//input[@name='password']");
    private final By buttonEnterLocator = By.xpath("//button[.//text()='Продолжить']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void login(String email, String password) {
        wait.until(d -> driver.findElement(inputEmailLocator).isDisplayed());

        driver.findElement(inputEmailLocator).sendKeys(email);
        driver.findElement(inputPasswordLocator).sendKeys(password);

        driver.findElement(buttonEnterLocator).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
