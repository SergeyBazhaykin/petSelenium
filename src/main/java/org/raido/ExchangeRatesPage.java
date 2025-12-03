package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRatesPage {
    private final WebDriver driver;
    private final int waitTimeInSeconds;

    private final By exchangeCardLocator = By.className("exchange-card");

    public ExchangeRatesPage(WebDriver driver) {
        this.driver = driver;
        this.waitTimeInSeconds = 5;
    }

    public List<WebElement> getExchangeRatesList() {
        return driver.findElements(exchangeCardLocator);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
