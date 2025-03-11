import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestOnlineReplenishmentWithoutCommission {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DisplayName("Проверка названия указанного блока")
    @Test
    public void checkTheNameOfTheSpecifiedBlock() {
        driver.get("https://mts.by");
        WebElement title = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String fullText = (String) js.executeScript("return arguments[0].innerText;", title);

        String expectedText = "Онлайн пополнение\nбез комиссии";
        assertEquals(expectedText, fullText.trim(), "Название блока не соответствует ожидаемому");
    }

    @DisplayName("Проверка логотипов платежных систем")
    @Test
    public void checkingPaymentSystemLogos() {
        driver.get("https://mts.by");
        String[] paymentSystemLogos = {
                "//img[contains(@src, 'visa')]",
                "//img[contains(@src, 'visa-verified')]",
                "//img[contains(@src, 'mastercard')]",
                "//img[contains(@src, 'mastercard-secure')]",
                "//img[contains(@src, 'belkart')]"
        };

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String xpath : paymentSystemLogos) {
            List<WebElement> logos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            assertTrue(!logos.isEmpty(), "Логотип не найден: " + xpath);
            assertTrue(logos.get(0).isDisplayed(), "Логотип не отображается: " + xpath);
        }
    }

    @DisplayName("Проверка кнопки 'Подробнее о сервисе'")
    @Test
    public void checkMoreAboutTheService() {
        driver.get("https://mts.by");
        String detailsButtonXpath = "//a[normalize-space()='Подробнее о сервисе']";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement detailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(detailsButtonXpath)));
            assertTrue(detailsButton.isDisplayed(), "Кнопка 'Подробнее о сервисе' не отображается");
            assertTrue(detailsButton.isEnabled(), "Кнопка 'Подробнее о сервисе' не активна");
        } catch (TimeoutException e) {
            fail("Кнопка 'Подробнее о сервисе' не найдена или не кликабельна: " + e.getMessage());
        }
    }

    @DisplayName("Проверка кнопки 'Продолжить'")
    @Test
    public void checkContinueButton() {
        driver.get("https://mts.by");
        String phoneInputXpath = "//input[@id='connection-phone']";
        String sumXpath = "//input[@id='connection-sum']";
        String emailXpath = "//input[@id='connection-email']";
        String continueButtonXpath = "//button[normalize-space()='Продолжить']";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(phoneInputXpath)));
        phoneInput.sendKeys("297777777");

        WebElement sum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sumXpath)));
        sum.sendKeys("150");

        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXpath)));
        email.sendKeys("woox899@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(continueButtonXpath)));
        assertTrue(continueButton.isDisplayed(), "Кнопка 'Продолжить' не отображается");
        assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' не активна");
    }

    @BeforeAll
    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}

