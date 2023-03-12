package ru.levelp.at.homework3;

import java.time.Duration;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class OpenMailDeleteMailTest {
    private static final String URL = "https://google.com";
    private static final String MAIL_RU = "https://mail.ru";
    private static WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.navigate().to(URL);
        driver.navigate().to(MAIL_RU);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /*   @AfterEach
       void tearDown() {
           driver.quit();
       }
   */
    @Test
    @Tag("delete")
    void workWithMailDeleteMail() throws InterruptedException {
        Thread sleepUtils;
        sleepUtils = new Thread();

        //нахожу кнопку для Авторизации в почте, нажимаю.
        WebElement loginButton = driver
            .findElement(By.xpath("//*[contains(@class,'resplash-btn')]"));
        loginButton.click();

        //нахожу фрейм, переключаюсь на него.
        WebElement frameElement = driver
            .findElement(By.xpath("//*[contains(@class,'ag-popup__frame__layout__iframe')]"));
        driver.switchTo().frame(frameElement);

        //нахожу поле для ввода Логина, ввожу Логин почты.
        WebDriverWait waitUserName = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitUserName.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[@id='login-content']//input[@name='username']")))
                    .sendKeys("lvlup_test");

        //нахожу кнопку для ввода пароля, нажимаю.
        WebElement passwordButton = driver
            .findElement(By.xpath("//*[contains(@class,'submit-button-wrap')]"));
        passwordButton.click();

        //нахожу поле пароля, ввожу его.
        WebDriverWait waitPassword = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitPassword.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[@id='login-content']//input[@name='password']")))
                    .sendKeys("Lomik2121!");

        //нахожу кнопку для входа, нажимаю.
        WebElement gateButton = driver.findElement(By.xpath("//*[contains(@class,'submit-button')]"));
        gateButton.click();

        //проверяю, что вошел по заголовку.
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");
        sleepUtils.sleep(3000);
        //перехожу в корзину
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")))
            .click();
        sleepUtils.sleep(3000);
        //считаю количество писем в корзине
        List<WebElement> emailsInBasket = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int beforeDeleteEmails = emailsInBasket.size();
        //System.out.println(BeforeDeleteEmails + " - количество писем корзине");
        sleepUtils.sleep(3000);
        //перехожу во входящие
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='sideBarContent']//a[@title = 'Входящие']")))
            .click();
        sleepUtils.sleep(3000);
        //проверяю количество писем во входящих стало.
        List<WebElement> emailsInTheInbox = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));
        sleepUtils.sleep(3000);
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int inboxEmails = emailsInTheInbox.size();
        //System.out.println(inboxEmails + " - количество писем в папке Входящие");

        //нахожу кнопку для написания Нового письма, нажимаю.
        driver.switchTo().defaultContent();
        WebDriverWait waitButtonNewLetter = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonNewLetter.until(ExpectedConditions.elementToBeClickable(By
                               .xpath("//*[@id='app-canvas']//span[@class='compose-button__wrapper']")))
                           .click();

        //нахожу поле для заполнения Получателя, ввожу адрес эл.почты.
        var recipient = "lvlup_test@mail.ru";
        WebDriverWait waitFillRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
                             .xpath("//div[@class='compose-app__compose']//input[@type='text']")))
                         .sendKeys(recipient);

        //нахожу поле для заполнения Темы письма, ввожу Тему.
        var topic = "И снова заполняю тему";
        WebDriverWait waitFillTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillTopic.until(ExpectedConditions.visibilityOfElementLocated(By
                         .xpath("//div[@class='compose-app__compose']//input[@name='Subject']")))
                     .sendKeys(topic);

        //нахожу поле для заполнения Тела письма, ввожу текст.
        var body = "И снова заполняю тело";
        WebDriverWait waitFillBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillBody.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//div[@class='compose-app__compose']//div[@role='textbox']")))
                    .sendKeys(body);

        //Нажимаю на кнопку отправить.
        WebDriverWait waitButtonSend = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonSend.until(ExpectedConditions.elementToBeClickable(By
                          .xpath(
                              "//div[@class='compose-app__compose']//button[@data-test-id='send']")))
                      .click();

        sleepUtils.sleep(3000);
        //закрываю всплывашку
        WebDriverWait waitButtonClose = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonClose.until(ExpectedConditions.elementToBeClickable(By
                           .xpath(
                               "//div[@class='layer-window__block']"
                                   + "//span[@class='button2__wrapper button2__wrapper_centered']")))
                       .click();

        sleepUtils.sleep(3000);
        //считаю количество писем во входящих после отправки
        sleepUtils.sleep(3000);
        List<WebElement> emailsInTheInboxAfterSend = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));
        sleepUtils.sleep(3000);
        //помещаю в переменную типа int, вывожу кол-во писем.
        int inboxEmailsAfterSend = emailsInTheInboxAfterSend.size();
        //System.out.println(inboxEmailsAfterSend + " - количество писем после отправки в папке Входящие");
        int mySendLetter = 1;
        sleepUtils.sleep(3000);
        //Проверяю, что во входящих стало больше на 1
        Assertions.assertThat(inboxEmails + mySendLetter).isEqualTo(inboxEmailsAfterSend);

        //Перехожу в свое письмо в папке Входящие.
        WebDriverWait waitMyDraft = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitMyDraft.until(ExpectedConditions.elementToBeClickable(By
                       .xpath(
                           "//*[@id='app-canvas']//span[@title='Nikita Ivanov <lvlup_test@mail.ru>']")))
                   .click();

        ///проверяю соответствие полей.

        //нахожу поле "Кому" и проверяю заполнение.
        WebDriverWait waitRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//span[@title='lvlup_test@mail.ru']")));

        Assertions.assertThat(recipient).isEqualTo("lvlup_test@mail.ru");

        //нахожу поле "Тема" и проверяю заполнение.
        WebDriverWait waitTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTopic.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//h2[@class='thread-subject']")));

        Assertions.assertThat(topic).isEqualTo("И снова заполняю тему");

        //нахожу поле "Текст" и проверяю заполнение.
        WebDriverWait waitBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitBody.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@class='letter-calendar']")));

        Assertions.assertThat(body).isEqualTo("И снова заполняю тело");
        sleepUtils.sleep(4000);
        //удаляю письмо
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app-canvas']//span[@title='Удалить']")))
            .click();

        sleepUtils.sleep(5000);
        //перехожу в корзину
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")))
            .click();
        sleepUtils.sleep(3000);
        //считаю количество писем в корзине теперь
        List<WebElement> emailsInBasketAfter = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));
        sleepUtils.sleep(3000);
        //помещаю в переменную типа int, вывожу кол-во писем.
        int afterDeleteEmails = emailsInBasketAfter.size();
        //System.out.println(AfterDeleteEmails + " - количество писем корзине теперь");

        Assertions.assertThat(beforeDeleteEmails + mySendLetter).isEqualTo(afterDeleteEmails);

        sleepUtils.sleep(3000);
        //выхожу из почты
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")))
            .click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")))
            .click();
    }
}
