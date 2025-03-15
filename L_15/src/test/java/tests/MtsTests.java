package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OnlineReplenishmentWithoutCommissionPage;
import pages.PaymentWindowPage;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTests {
    private static WebDriver driver;
    private static HomePage homePage;
    private static OnlineReplenishmentWithoutCommissionPage onlineReplenishmentWithoutCommissionPage;
    private static PaymentWindowPage paymentWindowPage;

    @BeforeEach
    public void setUp() {
        // Настройка WebDriver Manager для Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Инициализация Page Objects
        homePage = new HomePage(driver);
        onlineReplenishmentWithoutCommissionPage = new OnlineReplenishmentWithoutCommissionPage(driver);
        paymentWindowPage = new PaymentWindowPage(driver);

        // Открываем сайт mts.by
        driver.get("https://www.mts.by");
    }

    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    @Test
//    @Disabled
    public void testOnlineReplenishmentBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = homePage.checkTheNameOfTheSpecifiedBlock();
        assertEquals(expectedTitle, actualTitle.trim(), "Название блока не соответствует ожидаемому");
    }

    @DisplayName("Проверка наличия логотипов платежных систем")
    @Test
//    @Disabled
    public void testPaymentLogosPresence() {
        assertTrue(homePage.checkVisaLogo(), "Логотип Visa не отображается");
        assertTrue(homePage.checkVerifiedByVisaLogo(), "Логотип Verified by Visa не отображается");
        assertTrue(homePage.checkMasterCardLogo(), "Логотип Master Card не отображается");
        assertTrue(homePage.checkMasterCardSecureLogo(), "Логотип Master Card Secure не отображается");
        assertTrue(homePage.checkBelkartLogo(), "Логотип Белкарт не отображается");
    }

    //Проверка кнопки "Подробнее о сервисе"
    @DisplayName("Проверка кнопки 'Подробнее о сервисе'")
    @Test
//    @Disabled
    public void testDetailsButton() {
        assertTrue(homePage.checkDetailsButtonIsClickable(), "Кнопка 'Подробнее о сервисе' не кликабельна");
    }

    @DisplayName("Проверка кнопки 'Продолжить' после заполнения полей")
    @Test
//    @Disabled
    public void testContinueButton() {
        // Заполняем поля
        onlineReplenishmentWithoutCommissionPage.selectCommunicationServices();
        onlineReplenishmentWithoutCommissionPage.enterPhoneNumber("297777777");
        onlineReplenishmentWithoutCommissionPage.enterSum("150");
        onlineReplenishmentWithoutCommissionPage.enterEmail("woox899@gmail.com");

        // Проверяем, что кнопка "Продолжить" активна
        assertTrue(onlineReplenishmentWithoutCommissionPage.isContinueButtonEnabled(), "Кнопка 'Продолжить' не активна");
    }

    //Проверка плейсхолдеров Услуги связи
    @DisplayName("Проверка плейсхолдеров на вкладке 'Услуги связи'")
    @Test
//    @Disabled
    public void checkCommunicationServicesPlaceholders() {
        onlineReplenishmentWithoutCommissionPage.selectCommunicationServices();
        String expectedTitlePhoneNumber = "Номер телефона";
        String actualTitlePhoneNumber = onlineReplenishmentWithoutCommissionPage.checkCommunicationServicesPhoneNumberPlaceholder();
        assertEquals(expectedTitlePhoneNumber, actualTitlePhoneNumber.trim(), "Плейсхолдер 'Номер телефона' не совпадает");

        String expectedTitleSum = "Сумма";
        String actualTitleSum = onlineReplenishmentWithoutCommissionPage.checkCommunicationServicesSumPlaceholder();
        assertEquals(expectedTitleSum, actualTitleSum.trim(), "Плейсхолдер 'Сумма' не совпадает");

        String expectedTitleEmail = "E-mail для отправки чека";
        String actualTitleEmail = onlineReplenishmentWithoutCommissionPage.checkCommunicationServicesEmailPlaceholder();
        assertEquals(expectedTitleEmail, actualTitleEmail.trim(), "Плейсхолдер 'E-mail для отправки чека' не совпадает");
    }
    //Проверка плейсхолдеров Домашний интернет
    @DisplayName("Проверка плейсхолдеров на вкладке 'Домашний интернет'")
    @Test
//    @Disabled
    public void checkHomeInternetPlaceholders() {
        onlineReplenishmentWithoutCommissionPage.selectHomeInternet();
        String expectedTitlePhoneNumber = "Номер телефона";
        String actualTitlePhoneNumber = onlineReplenishmentWithoutCommissionPage.checkHomeInternetPhoneNumberPlaceholder();
        assertEquals(expectedTitlePhoneNumber, actualTitlePhoneNumber.trim(), "Плейсхолдер 'Номер телефона' не совпадает");

        String expectedTitleSum = "Сумма";
        String actualTitleSum = onlineReplenishmentWithoutCommissionPage.checkHomeInternetSumPlaceholder();
        assertEquals(expectedTitleSum, actualTitleSum.trim(), "Плейсхолдер 'Сумма' не совпадает");

        String expectedTitleEmail = "E-mail для отправки чека";
        String actualTitleEmail = onlineReplenishmentWithoutCommissionPage.checkHomeInternetEmailPlaceholder();
        assertEquals(expectedTitleEmail, actualTitleEmail.trim(), "Плейсхолдер 'E-mail для отправки чека' не совпадает");
    }

    //Проверка плейсхолдеров Рассрочка
    @DisplayName("Проверка плейсхолдеров на вкладке 'Рассрочка'")
    @Test
//    @Disabled
    public void checkInstallmentPlanPlaceholders() {
        onlineReplenishmentWithoutCommissionPage.selectInstallmentPlan();
        String expectedTitleScoreInstalment = "Номер счета на 44";
        String actualTitleScoreInstalment = onlineReplenishmentWithoutCommissionPage.checkInstallmentPlanScoreInstalmentPlaceholder();
        assertEquals(expectedTitleScoreInstalment, actualTitleScoreInstalment.trim(), "Плейсхолдер 'Номер счета на 44' не совпадает");

        String expectedTitleSum = "Сумма";
        String actualTitleSum = onlineReplenishmentWithoutCommissionPage.checkInstallmentPlanSumPlaceholder();
        assertEquals(expectedTitleSum, actualTitleSum.trim(), "Плейсхолдер 'Сумма' не совпадает");

        String expectedTitleEmail = "E-mail для отправки чека";
        String actualTitleEmail = onlineReplenishmentWithoutCommissionPage.checkInstallmentPlanEmailPlaceholder();
        assertEquals(expectedTitleEmail, actualTitleEmail.trim(), "Плейсхолдер 'E-mail для отправки чека' не совпадает");
    }

    //Проверка плейсхолдеров Задолженность
    @DisplayName("Проверка плейсхолдеров на вкладке 'Задолженность'")
    @Test
//    @Disabled
    public void checkScoreArrearsPlaceholders() {
        onlineReplenishmentWithoutCommissionPage.selectScoreArrears();

        String expectedTitleScoreInstalment = "Номер счета на 2073";
        String actualTitleScoreInstalment = onlineReplenishmentWithoutCommissionPage.checkScoreArrearsScoreInstalmentPlaceholder();
        assertEquals(expectedTitleScoreInstalment, actualTitleScoreInstalment.trim(), "Плейсхолдер 'Номер счета на 2073' не совпадает");

        String expectedTitleSum = "Сумма";
        String actualTitleSum = onlineReplenishmentWithoutCommissionPage.checkScoreArrearsSumPlaceholder();
        assertEquals(expectedTitleSum, actualTitleSum.trim(), "Плейсхолдер 'Сумма' не совпадает");

        String expectedTitleEmail = "E-mail для отправки чека";
        String actualTitleEmail = onlineReplenishmentWithoutCommissionPage.checkScoreArrearsEmailPlaceholder();
        assertEquals(expectedTitleEmail, actualTitleEmail.trim(), "Плейсхолдер 'E-mail для отправки чека' не совпадает");
    }

    @DisplayName("Заполнение полей, переход в окошко платежа, проверка данных в окошке платежа")
    @Test
    public void goToPaymentWindow() {
        paymentWindowPage.selectCommunicationServices();
        paymentWindowPage.enterPhoneNumber("297777777");
        paymentWindowPage.enterSum("150");
        paymentWindowPage.enterEmail("woox899@gmail.com");
        paymentWindowPage.pressContinueButton();
        paymentWindowPage.switchToPaymentFrame();
        paymentWindowPage.checkPaymentsLogos();

        String expectedTitleCostLabel = "150.00 BYN";
        String actualTitleCostLabel = paymentWindowPage.checkCostLabel();
        assertEquals(expectedTitleCostLabel, actualTitleCostLabel.trim(), "Сумма в окне оплаты не совпадает");

        String expectedTitlePhoneNumberLabel = "Оплата: Услуги связи Номер:375297777777";
        String actualTitlePhoneNumberLabel = paymentWindowPage.checkPhoneNumberLabel();
        assertEquals(expectedTitlePhoneNumberLabel, actualTitlePhoneNumberLabel.trim(), "Номер телефона не совпадает");

        String expectedTitlePayButtonLabel = "Оплатить 150.00 BYN";
        String actualTitlePayButtonLabel = paymentWindowPage.checkPayButtonLabel();
        assertEquals(expectedTitlePayButtonLabel, actualTitlePayButtonLabel, "Сумма на кнопке указана неверно");

        String expectedTitleCardNumberLabel = "Номер карты";
        String actualTitleCardNumberLabel = paymentWindowPage.checkCardNumberLabel();
        assertEquals(expectedTitleCardNumberLabel, actualTitleCardNumberLabel, "Тайтл 'Номер карты' некорректный");

        String expectedTitleValidityPeriodLabel = "Срок действия";
        String actualTitleValidityPeriodLabel = paymentWindowPage.checkValidityPeriodLabel();
        assertEquals(expectedTitleValidityPeriodLabel, actualTitleValidityPeriodLabel, "Тайтл 'Срок действия' некорректный");

        String expectedTitleCVCLabel = "CVC";
        String actualTitleCVCLabel = paymentWindowPage.checkCVCLabel();
        assertEquals(expectedTitleCVCLabel, actualTitleCVCLabel, "Тайтл 'CVC' некорректный");

        String expectedTitleOwnerNameLabel = "Имя держателя (как на карте)";
        String actualTitleOwnerNameLabel = paymentWindowPage.checkOwnerNameLabel();
        assertEquals(expectedTitleOwnerNameLabel, actualTitleOwnerNameLabel, "Тайтл 'Имя держателя (как на карте)' некорректный");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
