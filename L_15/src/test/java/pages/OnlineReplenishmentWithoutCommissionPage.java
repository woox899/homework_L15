package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class OnlineReplenishmentWithoutCommissionPage {
    private WebDriver driver;

    private By serviceDropdown = By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button");
    private By phoneInput = By.xpath("//input[@id='connection-phone']");
    private By sumInput = By.xpath("//input[@id='connection-sum']");
    private By emailInput = By.xpath("//input[@id='connection-email']");
    private By scoreInstalment = By.xpath("//input[@id='score-instalment']");
    private By scoreArrears = By.xpath("//input[@id='score-arrears']");
    private By continueButton = By.xpath("//button[normalize-space()='Продолжить']");

    public OnlineReplenishmentWithoutCommissionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCommunicationServices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        button.sendKeys("Услуги связи");
    }

    public void selectHomeInternet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        button.sendKeys("Домашний интернет");
    }

    public void selectInstallmentPlan() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        button.sendKeys("Рассрочка");
    }

    public void selectScoreArrears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        button.sendKeys("Задолженность");
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

    public boolean isContinueButtonEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        return button.isEnabled();
    }

    // Проверки плейсхолдеров для услуг связи
    // номер телефона на странице услуги связи
    public String checkCommunicationServicesPhoneNumberPlaceholder() {
        return driver.findElement(phoneInput).getAttribute("placeholder");
    }

    // сумма
    public String checkCommunicationServicesSumPlaceholder() {
        return driver.findElement(sumInput).getAttribute("placeholder");
    }

    //email
    public String checkCommunicationServicesEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    //Домашний интернет
    // номер телефона на странице домашний интернет
    public String checkHomeInternetPhoneNumberPlaceholder() {
        return driver.findElement(phoneInput).getAttribute("placeholder");
    }

    // сумма
    public String checkHomeInternetSumPlaceholder() {
        return driver.findElement(sumInput).getAttribute("placeholder");
    }

    //email
    public String checkHomeInternetEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    // Проверки плейсхолдеров раздела рассрочка
    //номер счета
    public String checkInstallmentPlanScoreInstalmentPlaceholder() {
        return driver.findElement(scoreInstalment).getAttribute("placeholder");
    }

    // сумма
    public String checkInstallmentPlanSumPlaceholder() {
        return driver.findElement(sumInput).getAttribute("placeholder");
    }

    //email
    public String checkInstallmentPlanEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    //Проверки плейсхлдеров для раздела Задолженность
    public String checkScoreArrearsScoreInstalmentPlaceholder() {
        return driver.findElement(scoreArrears).getAttribute("placeholder");
    }

    // сумма
    public String checkScoreArrearsSumPlaceholder() {
        return driver.findElement(sumInput).getAttribute("placeholder");
    }

    //email
    public String checkScoreArrearsEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }
}
