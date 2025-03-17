import org.junit.jupiter.api.*;
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
//    @Disabled
    public void checkTheNameOfTheSpecifiedBlock() {
        driver.get("https://mts.by");
        WebElement actualTitle = driver.findElement(By.xpath("//h2[normalize-space()='Онлайн пополнение без комиссии']"));
        String expectedText = "Онлайн пополнение\nбез комиссии";
        assertEquals(expectedText, actualTitle.getText(), "Название блока не соответствует ожидаемому");
    }

    @DisplayName("Проверка логотипов платежных систем")
    @Test
//    @Disabled
    public void checkingPaymentSystemLogos() {
        driver.get("https://mts.by");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement visaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'visa')]")));
        assertTrue(visaLogo.isDisplayed(), "Логотип Visa не отображается");

        WebElement visaVerifiedLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'visa-verified')]")));
        assertTrue(visaVerifiedLogo.isDisplayed(), "Логотип Visa Verifies не отображается");

        WebElement mastercardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'mastercard')]")));
        assertTrue(mastercardLogo.isDisplayed(), "Логотип Mastercard не отображается");

        WebElement mastercardSecureLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'mastercard-secure')]")));
        assertTrue(mastercardSecureLogo.isDisplayed(), "Логотип Mastercard Secure не отображается");

        WebElement belkartLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'belkart')]")));
        assertTrue(belkartLogo.isDisplayed(), "Логотип Belkart не отображается");
    }

    @DisplayName("Проверка кнопки 'Подробнее о сервисе'")
    @Test
//    @Disabled
    public void checkMoreAboutTheService() {
        driver.get("https://mts.by");
        String detailsButtonXpath = "//a[normalize-space()='Подробнее о сервисе']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement detailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(detailsButtonXpath)));
        detailsButton.click();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = driver.getCurrentUrl();
        assertTrue(actualUrl.startsWith(expectedUrl), "Переход на страницу с подробной информацией не выполнен");
    }

    @DisplayName("Проверка кнопки 'Продолжить'")
    @Test
//    @Disabled
    public void checkContinueButton() {
        driver.get("https://mts.by");
        String phoneInputXpath = "//input[@id='connection-phone']";
        String sumXpath = "//input[@id='connection-sum']";
        String emailXpath = "//input[@id='connection-email']";
        String continueButtonXpath = "//button[normalize-space()='Продолжить']";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(phoneInputXpath)));
        phoneInput.sendKeys("297777777");

        WebElement sum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sumXpath)));
        sum.sendKeys("150");

        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXpath)));
        email.sendKeys("woox899@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(continueButtonXpath)));

        continueButton.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
        WebElement payWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='150.00 BYN']")));
        assertTrue(payWindow.isDisplayed(), "Модальное окно не отображается");
    }

    @BeforeAll
    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}

