package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class InboxPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myMail;
    @FindBy(xpath = "//span[@title='lvlup_test@mail.ru']")
    private WebElement recipientMyMail;
    @FindBy(xpath = "//h2[@class='thread-subject']")
    private WebElement topicMyMail;
    @FindBy(xpath = "//div[@class='letter__body']")
    private WebElement bodyMyMail;
    @FindBy(xpath = "//*[@id='app-canvas']//span[@title='Удалить']")
    private WebElement deleteMyMail;
    @FindBy(xpath = "//div[@title='Входящие']")
    private WebElement inboxTitle;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public void clickMyMail() {
        wait.until(ExpectedConditions.elementToBeClickable(myMail)).click();
    }

    public void clickDeleteMyMail() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteMyMail)).click();
    }

    public String getRecipientMyMailText() {
        return wait.until(ExpectedConditions.visibilityOf(recipientMyMail)).getAttribute("title");
    }

    public String getTopicMyMailText() {
        return wait.until(ExpectedConditions.visibilityOf(topicMyMail)).getAttribute("textContent");
    }

    public String getBodyMyMailText() {
        return wait.until(ExpectedConditions.visibilityOf(bodyMyMail)).getAttribute("innerText");
    }

    public void getInboxTitleText() {
        wait.until(ExpectedConditions.visibilityOf(inboxTitle)).getText();
    }

}
