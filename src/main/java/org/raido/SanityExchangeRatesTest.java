package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SanityExchangeRatesTest extends BaseTest {

    @Test(description = "Проверка корректности курсов валют")
    public static void testSuccessfulLogin() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        ExchangeRatesPage exchangeRatesPage = new ExchangeRatesPage(driver);

        mainPage.clickExchangeRatesButtonOnMainPage();

        List<WebElement> exchangeRateCards = exchangeRatesPage.getExchangeRatesList();
        for (WebElement exchangeRateCard : exchangeRateCards) {
            WebElement name = exchangeRateCard.findElement(By.xpath("./div[1]/div[2]/div[@class='exchange-card__country__name']"));
            WebElement bye = exchangeRateCard.findElement(By.xpath("./div[2]"));
            WebElement sale = exchangeRateCard.findElement(By.xpath("./div[3]"));

            double byeValue = Double.parseDouble(bye.getText().trim());
            double saleValue = Double.parseDouble(sale.getText().trim());
            String valueName = name.getText().trim();

            double delta = 0.001;

            boolean isGreater = (byeValue - saleValue) > delta;

            softAssert.assertTrue(isGreater,
                    String.format("Ожидается, что  цена покупки %s %.2f больше, чем цена продажи %.2f с допуском %.3f.",
                            valueName, byeValue, saleValue, delta));

        }

        softAssert.assertAll();
    }
}
