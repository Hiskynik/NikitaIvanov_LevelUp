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

@Epic("Epic 2")
@Feature("Feature 2")
public class OpenMailTopicPOTestIT extends BaseMailTest {

    private Properties properties;
    private LoginPage loginPage;
    private IndexPageEmail indexPageEmail;
    private NewLetterWindow newLetterWindow;
    private ProfilePanel profilePanel;
    private AllFoldersPanel allFoldersPanel;
    private TestPage testPage;
    private SentPage sentPage;


    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        loginPage = new LoginPage(driver);
        indexPageEmail = new IndexPageEmail(driver);
        loginPage = new LoginPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        profilePanel = new ProfilePanel(driver);
        allFoldersPanel = new AllFoldersPanel(driver);
        testPage = new TestPage(driver);
        sentPage = new SentPage(driver);
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
//2
    @Story("Story 2")
    @DisplayName("Письмо с темой Test и работа с ним")
    @Test
    @Tag("topic")
    public void openMailWebsite() {

        final var emailname = properties
                .getProperty("email.name");
        final var emailpassword = properties
                .getProperty("email.password");
        final var indexpagetitle = properties
                .getProperty("index.page.title");
        final var recipient = properties
                .getProperty("recipient.for.mail.topic.test");
        final var topic = properties
                .getProperty("topic.for.mail.topic.test");
        final var body = properties
                .getProperty("body.for.mail.topic.test");

        //Вхожу в почту
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
        //перехожу во все папки, далее в папку Тест
        indexPageEmail.clickAllFoldersButton();
        allFoldersPanel.clickTestButton();
        testPage.waitForPageLoaded();
        List<WebElement> mails = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int topicTestMails = mails.size();
        System.out.println(topicTestMails
                + " - до сохранения в папку Тест");
        //перехожу в отправленные
        indexPageEmail.clickSentButton();
        sentPage.waitForPageLoaded();
        indexPageEmail.clickSentButton();
        List<WebElement> sendMails1 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        final int numberOfLettersSend = sendMails1.size();
        System.out.println(numberOfLettersSend
                + " - отправленных до");
        //пишу новое письмо
        indexPageEmail.clickNewLetterButton();
        newLetterWindow.fillRecipientTextField(recipient);
        newLetterWindow.fillTopicTextField(topic);
        newLetterWindow.fillBodyTextField(body);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseButtonAfterSending();
        //перехожу в отправленные
        indexPageEmail.clickSentButton();
        sentPage.waitForPageLoaded();
        indexPageEmail.clickSentButton();
        indexPageEmail.clickSentButton();
        List<WebElement> sendMails2 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        final int numberOfLettersSend1 = sendMails2.size();
        System.out.println(numberOfLettersSend1
                + " - отправленных после");
        int mySendLetter = 1;
        //проверка, что стало на 1 больше
        Assertions.assertThat(numberOfLettersSend + mySendLetter)
                .isEqualTo(numberOfLettersSend1);
        indexPageEmail.clickAllFoldersButton();
        allFoldersPanel.clickTestButton();
        testPage.waitForPageLoaded();
        List<WebElement> mails1 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        //помещаю в переменную типа int, вывожу кол-во писем.
        final int topicTestMails1 = mails1.size();
        System.out.println(topicTestMails1
                + " - после сохранения в папку Тест");
        var mySendTestLetter = 1;
        //Проверяю, что в папке Тест стало больше на 1
        Assertions.assertThat(topicTestMails + mySendTestLetter)
                .isEqualTo(topicTestMails1);
        //Открываю свое письмо в папке Тест
        testPage.clickMyTestMail();
        //Проверяю заполнение письма
        step("Проверка заполнения письма в папке Тест", () -> {
            var actualRecipient = testPage.getRecipientTestText();
            Assertions.assertThat(actualRecipient).isEqualTo(recipient);

            var actualTopic = testPage.getTopicTestText();
            Assertions.assertThat(actualTopic).isEqualTo(topic);

            var actualBody = testPage.getBodyTestText();
            Assertions.assertThat(actualBody).contains(body);
        });
        //Выхожу из почты
        indexPageEmail.clickProfileButton();
        profilePanel.clickExitButton();

    }
}
