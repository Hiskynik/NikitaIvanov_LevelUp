package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

//новое письмо
public class NewLetterWindow extends BasePage {
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            +
            "//input[@type='text']")
    private WebElement recipientTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            +
            "//input[@name='Subject']")
    private WebElement topicTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            + "//div[@role='textbox']")
    private WebElement bodyTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            + "//button[@data-test-id='send']")
    private WebElement sendButton;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            + "//button[@type='button']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@class='compose-collapsed']"
            + "//span[@class='button2__ico']")

    private WebElement closeButtonAfterSave;

    @FindBy(xpath = "//div[@class='layer-window__block']"
            + "//span[@class='button2__wrapper "
            + "button2__wrapper_centered']")
    private WebElement closeButtonAfterSending;

    public NewLetterWindow(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем получателя")
    public void fillRecipientTextField(final String recipient) {
        wait.until(ExpectedConditions
                .visibilityOf(recipientTextField)).sendKeys(recipient);
    }

    @Step("Заполняем тему письма")
    public void fillTopicTextField(final String topic) {
        wait.until(ExpectedConditions
                .visibilityOf(topicTextField)).sendKeys(topic);
    }

    @Step("Заполняем тело письма")
    public void fillBodyTextField(final String body) {
        wait.until(ExpectedConditions
                .visibilityOf(bodyTextField)).sendKeys(body);
    }

    @Step("Нажимаем отправить")
    public void clickSendButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(sendButton)).click();
    }

    @Step("Сохраняем письмо")
    public void clickSaveButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(saveButton)).click();
    }

    @Step("Закрываем всплывашку")
    public void clickCloseButtonAfterSave() {
        wait.until(ExpectedConditions
                .elementToBeClickable(closeButtonAfterSave)).click();

    }

    public void clickCloseButtonAfterSending() {
        wait.until(ExpectedConditions
                .elementToBeClickable(closeButtonAfterSending)).click();
    }
}

