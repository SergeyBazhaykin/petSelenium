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

    public List<ExchangeRateCard> getExchangeRateCards() {
        WaitUtils.waitForElementVisibility(driver, exchangeCardLocator, waitTimeInSeconds);

        List<WebElement> cardElements = driver.findElements(exchangeCardLocator);

        return cardElements.stream()
                .map(ExchangeRateCard::new) // Создаем объект внутреннего класса
                .collect(Collectors.toList());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static class ExchangeRateCard {

        private final WebElement cardRootElement;

        private static final By NAME_LOCATOR =
                By.xpath("./div[1]/div[2]/div[@class='exchange-card__country__name']");
        private static final By BUY_LOCATOR = By.xpath("./div[2]");
        private static final By SALE_LOCATOR = By.xpath("./div[3]");

        public ExchangeRateCard(WebElement cardRootElement) {
            this.cardRootElement = cardRootElement;
        }

        public String getCurrencyName() {
            return cardRootElement.findElement(NAME_LOCATOR).getText().trim();
        }

        public double getBuyValue() {
            String text = cardRootElement.findElement(BUY_LOCATOR).getText().trim();
            return Double.parseDouble(text);
        }

        public double getSaleValue() {
            String text = cardRootElement.findElement(SALE_LOCATOR).getText().trim();
            return Double.parseDouble(text);
        }
    }
}
