package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import javax.lang.model.element.Element;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By onlineReplenishmentWithoutCommissionTitle = By.xpath("//h2[normalize-space()='Онлайн пополнение без комиссии']");
    private By visaLogo = By.xpath("//img[contains(@src, 'visa')]");
    private By verifiedByVisaLogo = By.xpath( "//img[contains(@src, 'visa-verified')]");
    private By mastercardLogo = By.xpath("//img[contains(@src, 'mastercard')]");
    private By masterCardSecureLogo = By.xpath( "//img[contains(@src, 'mastercard-secure')]");
    private By belkartLogo = By.xpath("//img[contains(@src, 'belkart')]");
    private By detailsButton = By.xpath("//a[normalize-space()='Подробнее о сервисе']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String checkTheNameOfTheSpecifiedBlock() {
       return driver.findElement(onlineReplenishmentWithoutCommissionTitle).getText();
    }

    public boolean checkVisaLogo() {
       return driver.findElement(visaLogo).isDisplayed();
    }

    public boolean checkVerifiedByVisaLogo() {
        return driver.findElement(verifiedByVisaLogo).isDisplayed();
    }

    public boolean checkMasterCardLogo() {
        return driver.findElement(mastercardLogo).isDisplayed();
    }

    public boolean checkMasterCardSecureLogo() {
        return driver.findElement(masterCardSecureLogo).isDisplayed();
    }

    public boolean checkBelkartLogo() {
        return driver.findElement(belkartLogo).isDisplayed();
    }

    public boolean checkDetailsButtonIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(detailsButton));
        return button.isEnabled();
    }
}
