package org.raido.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.raido.BaseTest;
import org.raido.pages.ExchangeRatesPage;
import org.raido.pages.MainPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SanityExchangeRatesTest extends BaseTest {

    @Story("Проверка корректности курсов валют")
    @Owner("SergeyB")
    @Description("Сравнение цены покупки и цены продажи валют")
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
