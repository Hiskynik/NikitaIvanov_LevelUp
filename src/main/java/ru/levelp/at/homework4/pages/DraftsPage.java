package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DraftsPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myDraft;
    @FindBy(xpath = "//div[@class='compose-app__compose']//span[@class='text--1tHKB']")
    private WebElement recipientField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@name='Subject']")
    private WebElement topicField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//div[@role='textbox']")
    private WebElement bodyField;
    @FindBy(xpath = "//div[@title='Черновики']")
    private WebElement draftTitle;

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public void clickMyDraft() {
        wait.until(ExpectedConditions.elementToBeClickable(myDraft)).click();
    }

    public String getRecipientFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(recipientField)).getAttribute("innerText");
    }

    public String getTopicFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(topicField)).getAttribute("value");
    }

    public String getBodyFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(bodyField)).getAttribute("innerText");
    }

    public void getDraftTitleText() {
        wait.until(ExpectedConditions.visibilityOf(draftTitle)).getText();
    }
}
