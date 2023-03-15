package ru.levelp.at.homework3;

import java.time.Duration;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class OpenMailTestIT extends BaseMailTest {

    @Test
    @Tag("mail")
    void openMailWebsite() {

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
        WebDriverWait waitPassword = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitPassword.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[@id='login-content']//input[@name='password']")))
                    .sendKeys("Lomik2121!");

        //нахожу кнопку для входа, нажимаю.
        WebElement gateButton = driver.findElement(By.xpath("//*[contains(@class,'submit-button')]"));
        gateButton.click();

        //проверяю, что вошел по заголовку.
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");

        //перехожу в Отправленные
        WebDriverWait waitSendingEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();

        WebDriverWait waitSendingEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails2.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));

        List<WebElement> sendMails1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int numberOfLettersSend = sendMails1.size();
        //System.out.println(numberOfLettersSend + " - количество писем до отправки в папке Отправленные");

        //перехожу в черновики
        WebDriverWait waitButtonDrafts = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonDrafts.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();

        WebDriverWait waitDraftsEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitDraftsEmails1.until(ExpectedConditions.titleIs("Черновики - Почта Mail.ru"));

        List<WebElement> drafts = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__content']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int numberOfLetterToSave = drafts.size();
        //System.out.println(numberOfLetterToSave + " - количество писем до сохранения в папку Черновики");

        //нахожу кнопку для написания Нового письма, нажимаю.
        driver.switchTo().defaultContent();
        WebDriverWait waitButtonNewLetter = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonNewLetter.until(ExpectedConditions.elementToBeClickable(By
                               .xpath("//*[@id='app-canvas']//span[@class='compose-button__wrapper']")))
                           .click();

        //нахожу поле для заполнения Получателя, ввожу адрес эл.почты.
        var recipient = "lvluptestqa@gmail.com";
        WebDriverWait waitFillRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
                             .xpath("//div[@class='compose-app__compose']//input[@type='text']")))
                         .sendKeys(recipient);

        //нахожу поле для заполнения Темы письма, ввожу Тему.
        var topic = "New";
        WebDriverWait waitFillTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillTopic.until(ExpectedConditions.visibilityOfElementLocated(By
                         .xpath("//div[@class='compose-app__compose']//input[@name='Subject']")))
                     .sendKeys(topic);

        //нахожу поле для заполнения Тела письма, ввожу текст.
        var body = "Hello,world!";
        WebDriverWait waitFillBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillBody.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//div[@class='compose-app__compose']//div[@role='textbox']")))
                    .sendKeys(body);

        //нахожу кнопку Сохранить, нажимаю.
        WebDriverWait waitButtonSave = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitButtonSave.until(ExpectedConditions.elementToBeClickable(By
                          .xpath("//div[@class='compose-app__compose']//button[@type='button']")))
                      .click();

        //нахожу кнопку Закрыть, закрываю черновик.
        WebDriverWait waitButtonExit = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitButtonExit.until(ExpectedConditions.elementToBeClickable(By
                          .xpath(
                              "//div[@class ='compose-collapsed__item']"
                                  + "//span[@class ='button2__wrapper button2__wrapper_centered']")))
                      .click();
        //перехожу в черновики
        WebDriverWait waitButtonDrafts1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonDrafts1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();

        WebDriverWait waitDraftsEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitDraftsEmails2.until(ExpectedConditions.titleIs("Черновики - Почта Mail.ru"));

        //подсчитываю количество писем в черновиках.
        List<WebElement> drafts1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__content']"));

        //помещаю в переменную типа int, вывожу кол-во писем после сохранения черновика.
        int numberOfLetterAfterSave = drafts1.size();
        //System.out.println(numberOfLetterAfterSave + " - количество писем после сохранения в папке Черновики");
        int myLetter = 1;

        //Проверяю, что черновиков стало больше на 1
        Assertions.assertThat(numberOfLetterAfterSave - myLetter).isEqualTo(numberOfLetterToSave);

        //Перехожу в свое письмо в черновиках.
        WebDriverWait waitMyDraft = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitMyDraft.until(ExpectedConditions.elementToBeClickable(By
            .xpath(
                "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]"))).click();

        ///проверяю соответствие полей.

        //нахожу поле "Кому" и проверяю заполнение.
        WebDriverWait waitRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@class='compose-app__compose']//input[@type='text']")));

        Assertions.assertThat(recipient).isEqualTo("lvluptestqa@gmail.com");

        //нахожу поле "Тема" и проверяю заполнение.
        WebDriverWait waitTopic = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTopic.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@class='compose-app__compose']//input[@name='Subject']")));

        Assertions.assertThat(topic).isEqualTo("New");

        //нахожу поле "Текст" и проверяю заполнение.
        WebDriverWait waitBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitBody.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@class='compose-app__compose']//div[@role='textbox']")));

        Assertions.assertThat(body).isEqualTo("Hello,world!");

        //Нажимаю на кнопку отправить.
        WebDriverWait waitButtonSend = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonSend.until(ExpectedConditions.elementToBeClickable(By
                          .xpath(
                              "//div[@class='compose-app__compose']//button[@data-test-id='send']")))
                      .click();

        //закрываю всплывашку
        WebDriverWait waitButtonClose = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonClose.until(ExpectedConditions.elementToBeClickable(By
                           .xpath(
                               "//div[@class='layer-window__block']"
                                   + "//span[@class='button2__wrapper button2__wrapper_centered']")))
                       .click();

        //перехожу в черновики
        WebDriverWait waitDrafts1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitDrafts1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();
        WebDriverWait waitDrafts2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitDrafts2.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();


        WebDriverWait waitDraftsEmails4 = new WebDriverWait(driver, Duration.ofSeconds(100));
        waitDraftsEmails4.until(ExpectedConditions.titleIs("Черновики - Почта Mail.ru"));


        List<WebElement> drafts2 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем после сохранения черновика.
        int numberOfLetterAfterSend = drafts2.size();
        //System.out.println(numberOfLetterAfterSend + " - количество писем после отправки в папке Черновики");
        //проверяю, что письмо исчезло из Черновиков
        Assertions.assertThat(numberOfLetterAfterSend).isEqualTo(numberOfLetterToSave);

        //перехожу в Отправленные

        WebDriverWait waitSendEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendEmails1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();

        //считаю количество писем после отправки
        WebDriverWait waitSendingEmails4 = new WebDriverWait(driver, Duration.ofSeconds(80));
        waitSendingEmails4.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
        List<WebElement> sendMails = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        int numberOfLetterSend = sendMails.size();
        //System.out.println(numberOfLetterSend + " - количество писем после отправки в папке Отправленные");
        int mySendLetter = 1;

        //Проверяю, что в Отправленных стало больше на 1
        Assertions.assertThat(numberOfLettersSend + mySendLetter).isEqualTo(numberOfLetterSend);

        //выхожу из почты
        WebDriverWait waitProfile = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitProfile.until(ExpectedConditions.elementToBeClickable(
                       By.xpath("//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")))
                   .click();

        WebDriverWait waitExit = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitExit.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")))
                .click();
    }
}
