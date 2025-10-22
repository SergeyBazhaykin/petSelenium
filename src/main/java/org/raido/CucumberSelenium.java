package org.raido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CucumberSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://habr.com/en/companies/otus/articles/778376/");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        WebElement parentEl = driver.findElement(By.className("tm-company-basic-info"));

        List<WebElement> textMessages = parentEl.findElements(By.className("tm-description-list"));

        for (WebElement element : textMessages) {
            System.out.println("Paragraph text:" + element.getText());
        }

        driver.quit();
    }
}
