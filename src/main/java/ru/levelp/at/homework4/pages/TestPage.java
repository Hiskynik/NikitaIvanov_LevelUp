package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myTestMail;
    @FindBy(xpath = "//span[@title='lvlup_test@mail.ru']")
    private WebElement recipientTest;
    @FindBy(xpath = "//h2[@class='thread-subject']")
    private WebElement topicTest;
    @FindBy(xpath = "//div[@class='letter__body']")
    private WebElement bodyTest;
    @FindBy(xpath = "//div[@title='Test']")
    private WebElement testTitle;

    public TestPage(WebDriver driver) {
        super(driver);
    }

    public void clickMyTestMail() {
        wait.until(ExpectedConditions.elementToBeClickable(myTestMail)).click();
    }

    public String getRecipientTestText() {
        return wait.until(ExpectedConditions.visibilityOf(recipientTest)).getAttribute("title");
    }

    public String getTopicTestText() {
        return wait.until(ExpectedConditions.visibilityOf(topicTest)).getAttribute("textContent");
    }

    public String getBodyTestText() {
        return wait.until(ExpectedConditions.visibilityOf(bodyTest)).getAttribute("innerText");
    }

    public void getTestTitleText() {
        wait.until(ExpectedConditions.visibilityOf(testTitle)).getText();
    }
}
