package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By buttonLKLocator = By.xpath("//a[.//text()='Войти']");
    private final By buttonExchangeRatesLocator = By.xpath("//a[.//text()='Курсы валют']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickLoginButtonOnMainPage() {
        wait.until(d -> driver.findElement(buttonLKLocator).isDisplayed());
        driver.findElement(buttonLKLocator).click();
    }

    public void clickExchangeRatesButtonOnMainPage() {
        wait.until(d -> driver.findElement(buttonExchangeRatesLocator).isDisplayed());
        driver.findElement(buttonExchangeRatesLocator).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
