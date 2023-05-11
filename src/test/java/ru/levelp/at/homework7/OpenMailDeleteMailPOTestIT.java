package ru.levelp.at.homework7;

import static io.qameta.allure.Allure.step;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Epic("Epic 3")
@Feature("Feature 3")
public class OpenMailDeleteMailPOTestIT extends BaseMailTest {

    private Properties properties;
    private LoginPage loginPage;
    private IndexPageEmail indexPageEmail;
    private NewLetterWindow newLetterWindow;
    private ProfilePanel profilePanel;
    private InboxPage inboxPage;
    private BasketPage basketPage;


    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        loginPage = new LoginPage(driver);
        indexPageEmail = new IndexPageEmail(driver);
        loginPage = new LoginPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        profilePanel  = new ProfilePanel(driver);
        inboxPage = new InboxPage(driver);
        basketPage = new BasketPage(driver);
        properties = new Properties();

        try {
            properties.load(this
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Story("Store 3")
    @DisplayName("Создание/удаление письма")
    @Test
    @Tag("delete")
    public void openMailWebsite() {

        final var emailname = properties
                .getProperty("email.name");
        final var emailpassword = properties
                .getProperty("email.password");
        final var indexpagetitle = properties
                .getProperty("index.page.title");
        final var recipient = properties
                .getProperty("recipient.for.mail.delete.mail.test");
        final var topic = properties
                .getProperty("topic.for.mail.delete.mail.test");
        final var body = properties
                .getProperty("body.for.mail.delete.mail.test");

        //вхожу в почту
        loginPage.open();
        loginPage.clickLoginButton();
        loginPage.switchLoginFrame();
        loginPage.fillEmailTextField(emailname);
        loginPage.clickPasswordButton();
        loginPage.fillPasswordTextField(emailpassword);
        loginPage.clickEntranceButton();
        var title = driver.getTitle();
        Assertions.assertThat(title)
                .isEqualTo(indexpagetitle);
        //перехожу в корзину
        indexPageEmail.clickBasketButton();
        basketPage.waitForPageLoaded();
        //считаю количество писем в корзине
        List<WebElement> emailsInBasket = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int beforeDeleteEmails = emailsInBasket.size();
        System.out.println(beforeDeleteEmails
                + " - количество писем корзине");
        //перехожу во входящие
        indexPageEmail.clickIncomingButton();
        inboxPage.waitForPageLoaded();
        //проверяю количество писем во входящих.
        List<WebElement> emailsInTheInbox = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int inboxEmails = emailsInTheInbox.size();
        System.out.println(inboxEmails
                + " - количество писем в папке Входящие");
        //пишу новое письмо
        indexPageEmail.clickNewLetterButton();
        newLetterWindow.fillRecipientTextField(recipient);
        newLetterWindow.fillTopicTextField(topic);
        newLetterWindow.fillBodyTextField(body);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseButtonAfterSending();
        //перехожу во входящие
        indexPageEmail.clickIncomingButton();
        inboxPage.waitForPageLoaded();
        indexPageEmail.clickIncomingButton();
        indexPageEmail.clickIncomingButton();
        //считаю количество писем во входящих после отправки
        List<WebElement> emailsInTheInboxAfterSend = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        int inboxEmailsAfterSend = emailsInTheInboxAfterSend.size();
        System.out.println(inboxEmailsAfterSend
                + " - количество писем после отправки в папке Входящие");
        int myLetter = 1;
        //Проверяю, что во входящих стало больше на 1
        Assertions.assertThat(inboxEmails + myLetter)
                .isEqualTo(inboxEmailsAfterSend);
        //Перехожу в свое письмо во входящих
        inboxPage.clickMyMail();
        //Проверяю заполнение письма

        step("Проверка заполнения письма во входящих", () -> {
            var actualRecipient = inboxPage.getRecipientMyMailText();
            Assertions.assertThat(actualRecipient).isEqualTo(recipient);

            var actualTopic = inboxPage.getTopicMyMailText();
            Assertions.assertThat(actualTopic).isEqualTo(topic);

            var actualBody = inboxPage.getBodyMyMailText();
            Assertions.assertThat(actualBody).contains(body);
        });

        //Удаляю входящее письмо
        inboxPage.clickDeleteMyMail();
        //перехожу в корзину
        indexPageEmail.clickBasketButton();
        basketPage.waitForPageLoaded();
        //считаю количество писем в корзине

        List<WebElement> emailsInBasketAfter = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int afterDeleteEmails = emailsInBasketAfter.size();
        final int myLetter1 = 1;
        System.out.println(afterDeleteEmails
                + " - количество писем в корзине после");
        Assertions.assertThat(beforeDeleteEmails + myLetter1)
                .isEqualTo(afterDeleteEmails);
        //Выхожу из почты
        indexPageEmail.clickProfileButton();
        profilePanel.clickExitButton();


    }
}
