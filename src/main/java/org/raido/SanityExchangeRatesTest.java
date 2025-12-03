package org.raido;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SanityExchangeRatesTest extends BaseTest {

    @Test(description = "Проверка корректности курсов валют")
    public static void compareBuyAndSaleRatesInCards() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        ExchangeRatesPage exchangeRatesPage = new ExchangeRatesPage(driver);

        mainPage.clickExchangeRatesButtonOnMainPage();

        List<ExchangeRatesPage.ExchangeRateCard> exchangeRateCards = exchangeRatesPage.getExchangeRateCards();

        double delta = 0.001;

        for (ExchangeRatesPage.ExchangeRateCard card : exchangeRateCards) {
            String valueName = card.getCurrencyName();
            double byeValue = card.getBuyValue();
            double saleValue = card.getSaleValue();

            boolean isGreater = (byeValue - saleValue) > delta;

            softAssert.assertTrue(isGreater,
                    String.format("Ожидается, что цена покупки %s (%.2f) больше, чем цена продажи (%.2f) с допуском %.3f.",
                            valueName, byeValue, saleValue, delta));
        }

        softAssert.assertAll();
    }
}
