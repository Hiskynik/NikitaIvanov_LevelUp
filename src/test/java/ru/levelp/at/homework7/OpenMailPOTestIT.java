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

@Epic("Epic 1")
@Feature("Feature 1")
public class OpenMailPOTestIT extends BaseMailTest {

    private Properties properties;
    private LoginPage loginPage;
    private IndexPageEmail indexPageEmail;
    private DraftsPage draftsPage;
    private NewLetterWindow newLetterWindow;
    private ProfilePanel profilePanel;
    private SentPage sentPage;


    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        loginPage = new LoginPage(driver);
        indexPageEmail = new IndexPageEmail(driver);
        draftsPage = new DraftsPage(driver);
        loginPage = new LoginPage(driver);
        sentPage = new SentPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        profilePanel = new ProfilePanel(driver);
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


    @Story("Story 1")
    @DisplayName("Работа с черновиком")
    @Test
    @Tag("mail")
    public void openMailWebsite() {

        final var emailname = properties
                .getProperty("email.name");
        final var emailpassword = properties
                .getProperty("email.password");
        final var indexpagetitle = properties
                .getProperty("index.page.title");
        final var recipient = properties
                .getProperty("recipient.for.mail.test");
        final var topic = properties
                .getProperty("topic.for.mail.test");
        final var body = properties
                .getProperty("body.for.mail.test");

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
        //Перехожу в отправленные
        indexPageEmail.clickSentButton();
        sentPage.waitForPageLoaded();
        indexPageEmail.clickSentButton();
        List<WebElement> sendMails1 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        final int numberOfLettersSend = sendMails1.size();
        System.out.println(numberOfLettersSend
                + " - отправленных до");
        //Перехожу в черновики
        indexPageEmail.clickDraftsButton();
        draftsPage.waitForPageLoaded();

        List<WebElement> drafts = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__content']"));
        final int numberOfLetterToSave = drafts.size();
        System.out.println(numberOfLetterToSave
                + " - черновиков до");
        //Пишу новое письмо
        indexPageEmail.clickNewLetterButton();
        newLetterWindow.fillRecipientTextField(recipient);
        newLetterWindow.fillTopicTextField(topic);
        newLetterWindow.fillBodyTextField(body);
        newLetterWindow.clickSaveButton();
        newLetterWindow.clickCloseButtonAfterSave();

        indexPageEmail.clickDraftsButton();
        draftsPage.waitForPageLoaded();

        List<WebElement> drafts1 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__content']"));
        int numberOfLetterAfterSave = drafts1.size();
        System.out.println(numberOfLetterAfterSave
                + " - черновиков после");
        int myLetter = 1;
        Assertions.assertThat(numberOfLetterAfterSave - myLetter)
                .isEqualTo(numberOfLetterToSave);
        //Перехожу в черновики.Проверяю соответствие заполнения
        draftsPage.clickMyDraft();
        step("Проверка заполнения письма в черновиках", () -> {
            var actualRecipient = draftsPage.getRecipientFieldText();
            Assertions.assertThat(actualRecipient).isEqualTo(recipient);

            var actualTopic = draftsPage.getTopicFieldText();
            Assertions.assertThat(actualTopic).isEqualTo(topic);

            var actualBody = draftsPage.getBodyFieldText();
            Assertions.assertThat(actualBody).contains(body);
        });

        //Отправляю письмо
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseButtonAfterSending();
        //Перехожу в отправленные
        indexPageEmail.clickSentButton();
        sentPage.waitForPageLoaded();
        indexPageEmail.clickSentButton();
        List<WebElement> sendMails2 = driver.findElements(By
                .xpath("//*[@id='app-canvas']"
                        + "//div[@class='llc__background']"));
        int numberOfLettersSend2 = sendMails2.size();
        int mySendLetter = 1;
        System.out.println(numberOfLettersSend2
                + " - отправленных после");
        Assertions.assertThat(numberOfLettersSend + mySendLetter)
                .isEqualTo(numberOfLettersSend2);
        //Выхожу из почты
        indexPageEmail.clickProfileButton();
        profilePanel.clickExitButton();

    }
}

