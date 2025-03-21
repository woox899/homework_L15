package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentWindowPage {
    private WebDriver driver;
    private By iFrame = By.xpath("//iframe[@class='bepaid-iframe']");
    private By serviceDropdown = By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button");
    private By phoneInput = By.xpath("//input[@id='connection-phone']");
    private By sumInput = By.xpath("//input[@id='connection-sum']");
    private By emailInput = By.xpath("//input[@id='connection-email']");
    private By continueButton = By.xpath("//button[normalize-space()='Продолжить']");
    private By costLabel = By.xpath("//span[text()='150.00 BYN']");
    private By phoneNumberLabel = By.xpath("//span[normalize-space()='Оплата: Услуги связи Номер:375297777777']");
    private By payButtonLabel = By.xpath("//button[normalize-space()='Оплатить 150.00 BYN']");

    private By cardNumberLabel = By.xpath("//label[text()='Номер карты']");
    private By validityPeriodLabel = By.xpath("//label[text()='Срок действия']");
    private By cvcLabel = By.xpath("//label[text()='CVC']");
    private By ownerNameLabel = By.xpath("//label[text()='Имя держателя (как на карте)']");

    private String[] paymentSystemLogos = {
            "//img[contains(@src, 'visa-system')]",
            "//img[contains(@src, 'mastercard-system')]",
            "//img[contains(@src, 'belkart-system')]",
            "//img[contains(@src, 'maestro-system')]",
            "//img[contains(@src, 'mir-system')]"
    };

    public PaymentWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCommunicationServices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        button.sendKeys("Услуги связи");
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneInput).sendKeys(phoneNumber);
    }

    public void enterSum(String sum) {
        driver.findElement(sumInput).sendKeys(sum);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void pressContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
    }

    public void switchToPaymentFrame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
    }

    public String checkCostLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement costLabelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(costLabel));
        return costLabelElement.getText();
    }

    public String checkPhoneNumberLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement phoneNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberLabel));
        return phoneNumberElement.getText();
    }

    public String checkPayButtonLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonLabelElement = wait.until(ExpectedConditions.presenceOfElementLocated(payButtonLabel));
        return buttonLabelElement.getText();
    }

    public String checkCardNumberLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cardNumberLabelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberLabel));
        return cardNumberLabelElement.getText();
    }

    public String checkValidityPeriodLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement validityPeriodLabelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(validityPeriodLabel));
        return validityPeriodLabelElement.getText();
    }

    public String checkCVCLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement validityPeriodLabelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cvcLabel));
        return validityPeriodLabelElement.getText();
    }

    public String checkOwnerNameLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ownerNameLabelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ownerNameLabel));
        return ownerNameLabelElement.getText();
    }

    public void checkPaymentsLogos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (String xpath : paymentSystemLogos) {
            List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            assertTrue(!logos.isEmpty(), "Логотип не найден: " + xpath);
            assertTrue(logos.get(0).isDisplayed(), "Логотип не отображается: " + xpath);
        }
    }
}
