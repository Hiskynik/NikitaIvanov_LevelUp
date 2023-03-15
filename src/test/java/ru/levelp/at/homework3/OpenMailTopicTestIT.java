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

class OpenMailTopicTestIT extends BaseMailTest {

    @Test
    @Tag("topic")
    void workWithTopicMail() {

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

        //перехожу во Все папки
        WebDriverWait waitALLFolders = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitALLFolders.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@class = 'sidebar__header']//a[@title = 'Все папки']"))).click();

        //перехожу в папку Тест
        WebDriverWait waitTestFolder = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTestFolder.until(ExpectedConditions.elementToBeClickable(
                          By.xpath(
                              "//div[@class='sidebar__full fn-enter']"
                                  + "//a[@class = 'nav__item nav__item_expanded_true "
                                  + "nav__item_child-level_0']")))
                      .click();

        WebDriverWait waitTestFolder1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitTestFolder1.until(ExpectedConditions.titleContains("Test - Почта Mail.ru"));

        List<WebElement> mails = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int topicTestMails = mails.size();
        System.out.println(topicTestMails + " - количество писем до сохранения в папку Тест");

        //перехожу в Отправленные
        WebDriverWait waitSendingEmails1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();

        WebDriverWait waitSendingEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitSendingEmails2.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
        //считаю отправленные
        List<WebElement> sendMails1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int a = sendMails1.size();
        System.out.println(a + " - количество писем до отправки в папке Отправленные");

        //нахожу кнопку для Нового письма, нажимаю.
        driver.switchTo().defaultContent();
        WebDriverWait waitButtonNewLetter = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitButtonNewLetter.until(ExpectedConditions.elementToBeClickable(By
                               .xpath("//*[@id='app-canvas']"
                                   + "//span[@class='compose-button__wrapper']")))
                           .click();

        //нахожу поле для заполнения Получателя, ввожу адрес эл.почты.
        var recipient = "lvlup_test@mail.ru";
        WebDriverWait waitFillRecipient = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitFillRecipient.until(ExpectedConditions.visibilityOfElementLocated(By
                             .xpath("//div[@class='compose-app__compose']//input[@type='text']")))
                         .sendKeys(recipient);

        //нахожу поле для заполнения Темы письма, ввожу Тему.
        var topic = "Test";
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

        //Нажимаю на кнопку отправить.
        WebDriverWait waitButtonSend = new WebDriverWait(driver, Duration.ofSeconds(50));
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

        WebDriverWait waitSendingEmails3 = new WebDriverWait(driver, Duration.ofSeconds(100));
        waitSendingEmails3.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));

        //считаю отправленные
        List<WebElement> mailsForSend = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int b = mailsForSend.size();
        System.out.println(b + " - количество писем после отправки в папке Отправленные");
        final int mySendLetter = 1;

        //Проверяю, что в Отправленных стало больше на 1
        Assertions.assertThat(a + mySendLetter).isEqualTo(b);

        //перехожу во Все папки
        WebDriverWait waitALLFolders1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitALLFolders1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@class = 'sidebar__header']//a[@title = 'Все папки']"))).click();

        //перехожу в папку Тест
        WebDriverWait waitTestFolder2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTestFolder2.until(ExpectedConditions.elementToBeClickable(
                           By.xpath("//div[@class='sidebar__full fn-enter']"
                               + "//a[@class = 'nav__item nav__item_expanded_true nav__item_child-level_0']")))
                       .click();
        WebDriverWait waitTestFolder3 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitTestFolder3.until(ExpectedConditions.titleContains("Test - Почта Mail.ru"));
        //считаю количество писем после отправки
        List<WebElement> mails1 = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int topicTestMailsAfterSending = mails1.size();
        System.out.println(topicTestMailsAfterSending + " - количество писем после отправки в папке Тест");
        final int mySendTestLetter = 1;

        //Проверяю, что в папке Тест стало больше на 1
        Assertions.assertThat(topicTestMailsAfterSending - mySendTestLetter).isEqualTo(topicTestMails);

        //Перехожу в свое письмо в папке Тест.
        WebDriverWait waitMyDraft = new WebDriverWait(driver, Duration.ofSeconds(20));
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

        Assertions.assertThat(topic).isEqualTo("Test");

        //нахожу поле "Текст" и проверяю заполнение.
        WebDriverWait waitBody = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitBody.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@class='letter-calendar']")));

        Assertions.assertThat(body).isEqualTo("Hello,world!");

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

