package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class NewLetterWindow extends BasePage {
    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@type='text']")
    private WebElement recipientTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@name='Subject']")
    private WebElement topicTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//div[@role='textbox']")
    private WebElement bodyTextField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//button[@data-test-id='send']")
    private WebElement sendButton;
    @FindBy(xpath = "//div[@class='compose-app__compose']//button[@type='button']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@class ='compose-collapsed__item']"
            + "//span[@class ='button2__wrapper button2__wrapper_centered']")
    private WebElement closeButton;

    public NewLetterWindow(WebDriver driver) {
        super(driver);
    }

    public void fillRecipientTextField(final String recipient) {
        wait.until(ExpectedConditions.visibilityOf(recipientTextField)).sendKeys(recipient);
    }

    public void fillTopicTextField(final String topic) {
        wait.until(ExpectedConditions.visibilityOf(topicTextField)).sendKeys(topic);
    }

    public void fillBodyTextField(final String body) {
        wait.until(ExpectedConditions.visibilityOf(bodyTextField)).sendKeys(body);
    }

    public void clickSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
}

