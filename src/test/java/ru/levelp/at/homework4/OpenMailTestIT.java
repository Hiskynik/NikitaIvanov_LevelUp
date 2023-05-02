package ru.levelp.at.homework4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.homework4.pages.*;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class OpenMailTestIT extends BaseMailTest {

    private Properties properties;

    public OpenMailTestIT() {
    }

    @Override
    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ThreadLocal<LoginPage> loginPage = ThreadLocal.withInitial(() -> new LoginPage(driver));
    private final ThreadLocal<IndexPageEmail> indexPageEmail = ThreadLocal.withInitial(() -> new IndexPageEmail(driver));
    private final ThreadLocal<NewLetterWindow> newLetterWindow = ThreadLocal.withInitial(() -> new NewLetterWindow(driver));
    private final ThreadLocal<DraftsPage> draftsPage = ThreadLocal.withInitial(() -> new DraftsPage(driver));

    @Test
    @Tag("mail")
    public void openMailWebsite() {

        var emailname = properties.getProperty("email.name");
        var emailpassword = properties.getProperty("email.password");
        var indexpagetitle = properties.getProperty("index.page.title");
        var recipient = properties.getProperty("recipient");
        var topic = properties.getProperty("topic");
        var body = properties.getProperty("body");

        loginPage.get().open();
        loginPage.get().clickLoginButton();
        loginPage.get().switchLoginFrame();
        loginPage.get().fillEmailTextField(emailname);
        loginPage.get().clickPasswordButton();
        loginPage.get().fillPasswordTextField(emailpassword);
        loginPage.get().clickEntranceButton();
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo(indexpagetitle);
        indexPageEmail.get().clickSentButton();
        WebDriverWait waitSendingEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails1.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
        List<WebElement> sendMails1 = driver.findElements(By.xpath("//*[@id='app-canvas']//div[@class='llc__background']"));
        final int numberOfLettersSend = sendMails1.size();
        System.out.println(numberOfLettersSend + " - отправленных до");
        indexPageEmail.get().clickDraftsButton();
        List<WebElement> drafts = driver.findElements(By.xpath("//*[@id='app-canvas']//div[@class='llc__content']"));
        final int numberOfLetterToSave = drafts.size();
        System.out.println(numberOfLetterToSave + " - черновиков до");
        indexPageEmail.get().clickNewLetterButton();
        newLetterWindow.get().fillRecipientTextField(recipient);
        newLetterWindow.get().fillTopicTextField(topic);
        newLetterWindow.get().fillBodyTextField(body);
        newLetterWindow.get().clickSaveButton();
        newLetterWindow.get().clickCloseButton();
        indexPageEmail.get().clickDraftsButton();
        WebDriverWait waitDraftsEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitDraftsEmails2.until(ExpectedConditions.titleIs("Черновики - Почта Mail.ru"));
        List<WebElement> drafts1 = driver.findElements(By.xpath("//*[@id='app-canvas']//div[@class='llc__content']"));
        int numberOfLetterAfterSave = drafts1.size();
        System.out.println(numberOfLetterAfterSave + " - черновиков после");
        int myLetter = 1;
        Assertions.assertThat(numberOfLetterAfterSave - myLetter).isEqualTo(numberOfLetterToSave);
        //draftsPage.clickMyDraft();

        var actualRecipient = MyDraftWindow.getRecipientFieldText();
        Assertions.assertThat(actualRecipient).isEqualTo(recipient);

        var actualTopic = MyDraftWindow.getTopicFieldText();
        Assertions.assertThat(actualTopic).isEqualTo(topic);

        var actualBody = MyDraftWindow.getBodyFieldText();
        Assertions.assertThat(actualBody).isEqualTo(body);




/*
        WebElement loginButton = driver
                .findElement(By.xpath("//*[contains(@class,'resplash-btn')]"));
        loginButton.click();
        //?????? ?????, ???????????? ?? ????.
        WebElement frameElement = driver
                .findElement(By.xpath("//*[contains(@class,'ag-popup__frame__layout__iframe')]"));
        driver.switchTo().frame(frameElement);

    }

    //?????? ???? ??? ????? ??????, ????? ????? ?????.
    WebDriverWait waitUserName = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitUserName.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='username']")))
                .

    sendKeys("lvlup_test");

    public void emailTextField(final String email) {
        loginButton.click();
    }

    //?????? ?????? ??? ????? ??????, ???????.
    WebElement passwordButton = driver
            .findElement(By.xpath("//*[contains(@class,'submit-button-wrap')]"));
        passwordButton.click();


    //?????? ???? ??????, ????? ???.
    WebDriverWait waitPassword = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitPassword.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='password']")))
                .

    sendKeys("Lomik2121!");

    //?????? ?????? ??? ?????, ???????.
    WebElement gateButton = driver.findElement(By.xpath("//*[contains(@class,'submit-button')]"));
        gateButton.click();

    //????????, ??? ????? ?? ?????????.
    var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: ?????, ????? ? ?????????, ???????, ????");

    //???????? ? ????????????
    WebDriverWait waitSendingEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails1.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'????????????')]"))).click();

    WebDriverWait waitSendingEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails2.until(ExpectedConditions.titleIs("???????????? - ????? Mail.ru"));

    List<WebElement> sendMails1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

    //??????? ? ?????????? ???? int, ?????? ???-?? ?????.
    final int numberOfLettersSend = sendMails1.size();
    //System.out.println(numberOfLettersSend + " - ?????????? ????? ?? ???????? ? ????? ????????????");

    //???????? ? ?????????
    WebDriverWait waitButtonDrafts = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonDrafts.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'?????????')]"))).

    click();

    WebDriverWait waitDraftsEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitDraftsEmails1.until(ExpectedConditions.titleIs("????????? - ????? Mail.ru"));

    List<WebElement> drafts = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__content']"));

    //??????? ? ?????????? ???? int, ?????? ???-?? ?????.
    final int numberOfLetterToSave = drafts.size();
    //System.out.println(numberOfLetterToSave + " - ?????????? ????? ?? ?????????? ? ????? ?????????");

    //?????? ?????? ??? ????????? ?????? ??????, ???????.
        driver.switchTo().defaultContent();
    WebDriverWait waitButtonNewLetter = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonNewLetter.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[@id='app-canvas']//span[@class='compose-button__wrapper']")))
                .

    click();

    //?????? ???? ??? ?????????? ??????????, ????? ????? ??.?????.
    var recipient = "lvluptestqa@gmail.com";
    WebDriverWait waitFillRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//input[@type='text']")))
                .sendKeys(recipient);

    //?????? ???? ??? ?????????? ???? ??????, ????? ????.
    var topic = "New";
    WebDriverWait waitFillTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillTopic.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//input[@name='Subject']")))
                .sendKeys(topic);

    //?????? ???? ??? ?????????? ???? ??????, ????? ?????.
    var body = "Hello,world!";
    WebDriverWait waitFillBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillBody.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//div[@role='textbox']")))
                .sendKeys(body);

    //?????? ?????? ?????????, ???????.
    WebDriverWait waitButtonSave = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitButtonSave.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//div[@class='compose-app__compose']//button[@type='button']")))
                .click();

    //?????? ?????? ???????, ???????? ????????.
    WebDriverWait waitButtonExit = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitButtonExit.until(ExpectedConditions.elementToBeClickable(By
                .xpath(
                "//div[@class ='compose-collapsed__item']"
                +"//span[@class ='button2__wrapper button2__wrapper_centered']")))
                .

    click();

    //???????? ? ?????????
    WebDriverWait waitButtonDrafts1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonDrafts1.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'?????????')]"))).

    click();

    WebDriverWait waitDraftsEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitDraftsEmails2.until(ExpectedConditions.titleIs("????????? - ????? Mail.ru"));

    //??????????? ?????????? ????? ? ??????????.
    List<WebElement> drafts1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__content']"));

    //??????? ? ?????????? ???? int, ?????? ???-?? ????? ????? ?????????? ?????????.
    int numberOfLetterAfterSave = drafts1.size();
    //System.out.println(numberOfLetterAfterSave + " - ?????????? ????? ????? ?????????? ? ????? ?????????");
    int myLetter = 1;

    //????????, ??? ?????????? ????? ?????? ?? 1
        Assertions.assertThat(numberOfLetterAfterSave -myLetter).

    isEqualTo(numberOfLetterToSave);

    //???????? ? ???? ?????? ? ??????????.
    WebDriverWait waitMyDraft = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitMyDraft.until(ExpectedConditions.elementToBeClickable(By
                .xpath(
                "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]"))).

    click();

    ///???????? ???????????? ?????.

    //?????? ???? "????" ? ???????? ??????????.
    WebDriverWait waitRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//input[@type='text']")));

        Assertions.assertThat(recipient).

    isEqualTo("lvluptestqa@gmail.com");

    //?????? ???? "????" ? ???????? ??????????.
    WebDriverWait waitTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTopic.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//input[@name='Subject']")));

        Assertions.assertThat(topic).

    isEqualTo("New");

    //?????? ???? "?????" ? ???????? ??????????.
    WebDriverWait waitBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitBody.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']//div[@role='textbox']")));

        Assertions.assertThat(body).

    isEqualTo("Hello,world!");

    //??????? ?? ?????? ?????????.
    WebDriverWait waitButtonSend = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonSend.until(ExpectedConditions.elementToBeClickable(By
                .xpath(
                "//div[@class='compose-app__compose']//button[@data-test-id='send']")))
                .

    click();

    //???????? ??????????
    WebDriverWait waitButtonClose = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonClose.until(ExpectedConditions.elementToBeClickable(By
                .xpath(
                "//div[@class='layer-window__block']"
                +"//span[@class='button2__wrapper button2__wrapper_centered']")))
                .

    click();

    //???????? ? ?????????
    WebDriverWait waitDrafts1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitDrafts1.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'?????????')]"))).

    click();

    WebDriverWait waitDrafts2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitDrafts2.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'?????????')]"))).

    click();


    WebDriverWait waitDraftsEmails4 = new WebDriverWait(driver, Duration.ofSeconds(100));
        waitDraftsEmails4.until(ExpectedConditions.titleIs("????????? - ????? Mail.ru"));


    List<WebElement> drafts2 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

    //??????? ? ?????????? ???? int, ?????? ???-?? ????? ????? ?????????? ?????????.
    int numberOfLetterAfterSend = drafts2.size();
    //System.out.println(numberOfLetterAfterSend + " - ?????????? ????? ????? ???????? ? ????? ?????????");
    //????????, ??? ?????? ??????? ?? ??????????
        Assertions.assertThat(numberOfLetterAfterSend).

    isEqualTo(numberOfLetterToSave);

    //???????? ? ????????????

    WebDriverWait waitSendEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendEmails1.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class, 'nav__item') and contains(.,'????????????')]"))).

    click();

    //?????? ?????????? ????? ????? ????????
    WebDriverWait waitSendingEmails4 = new WebDriverWait(driver, Duration.ofSeconds(80));
        waitSendingEmails4.until(ExpectedConditions.titleIs("???????????? - ????? Mail.ru"));
    List<WebElement> sendMails = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

    //??????? ? ?????????? ???? int, ?????? ???-?? ?????.
    int numberOfLetterSend = sendMails.size();
    //System.out.println(numberOfLetterSend + " - ?????????? ????? ????? ???????? ? ????? ????????????");
    int mySendLetter = 1;

    //????????, ??? ? ???????????? ????? ?????? ?? 1
        Assertions.assertThat(numberOfLettersSend +mySendLetter).

    isEqualTo(numberOfLetterSend);

    //?????? ?? ?????
    WebDriverWait waitProfile = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitProfile.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")))
                .

    click();

    WebDriverWait waitExit = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitExit.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")))
                .

    click();

 */
    }
}
