package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ExchangeRatesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By exchangeCardLocator = By.className("exchange-card");

    public ExchangeRatesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public List<WebElement> getExchangeRatesList() {
        return driver.findElements(exchangeCardLocator);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
