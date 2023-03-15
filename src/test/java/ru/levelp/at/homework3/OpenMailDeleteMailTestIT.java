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

class OpenMailDeleteMailTestIT extends BaseMailTest {

    @Test
    @Tag("delete")
    void workWithMailDeleteMail() {

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

        //перехожу в корзину
        WebDriverWait waitBasket = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitBasket.until(ExpectedConditions.elementToBeClickable(
                      By.xpath("//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")))
                  .click();

        WebDriverWait waitTestFolder = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitTestFolder.until(ExpectedConditions.titleContains("Корзина - Почта Mail.ru"));

        //считаю количество писем в корзине

        List<WebElement> emailsInBasket = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int beforeDeleteEmails = emailsInBasket.size();
        System.out.println(beforeDeleteEmails + " - количество писем корзине");

        //перехожу во входящие
        WebDriverWait waitIncoming = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitIncoming.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Входящие')]"))).click();

        WebDriverWait waitIncomingFolder = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitIncomingFolder.until(ExpectedConditions.titleContains("Входящие - Почта Mail.ru"));
        //проверяю количество писем во входящих стало.
        List<WebElement> emailsInTheInbox = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int inboxEmails = emailsInTheInbox.size();
        System.out.println(inboxEmails + " - количество писем в папке Входящие");

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

        //закрываю всплывашку
        WebDriverWait waitButtonClose = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitButtonClose.until(ExpectedConditions.elementToBeClickable(By
                           .xpath(
                               "//div[@class='layer-window__block']"
                                   + "//span[@class='button2__wrapper button2__wrapper_centered']")))
                       .click();
        //перехожу во входящие
        WebDriverWait waitIncoming1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitIncoming1.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Входящие')]"))).click();

        WebDriverWait waitIncoming2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitIncoming2.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@class, 'nav__item') and contains(.,'Входящие')]"))).click();

        WebDriverWait waitIncomingFolderAfter = new WebDriverWait(driver, Duration.ofSeconds(80));
        waitIncomingFolderAfter.until(ExpectedConditions.titleContains("Входящие - Почта Mail.ru"));

        //считаю количество писем во входящих после отправки
        List<WebElement> emailsInTheInboxAfterSend = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int inboxEmailsAfterSend = emailsInTheInboxAfterSend.size();
        System.out.println(inboxEmailsAfterSend + " - количество писем после отправки в папке Входящие");
        final int mySendLetter = 1;

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

        //удаляю письмо
        WebDriverWait waitEmailDelete = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitEmailDelete.until(ExpectedConditions.elementToBeClickable(
                           By.xpath("//*[@id='app-canvas']//span[@title='Удалить']")))
                       .click();

        //перехожу в корзину
        WebDriverWait waitBasket2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitBasket2.until(ExpectedConditions.elementToBeClickable(
                       By.xpath("//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")))
                   .click();
        WebDriverWait waitTestFolder1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        waitTestFolder1.until(ExpectedConditions.titleContains("Корзина - Почта Mail.ru"));

        //считаю количество писем в корзине теперь
        List<WebElement> emailsInBasketAfter = driver.findElements(By
            .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));

        //помещаю в переменную типа int, вывожу кол-во писем.
        final int afterDeleteEmails = emailsInBasketAfter.size();
        System.out.println(afterDeleteEmails + " - количество писем в корзине теперь");

        Assertions.assertThat(beforeDeleteEmails + mySendLetter).isEqualTo(afterDeleteEmails);

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
