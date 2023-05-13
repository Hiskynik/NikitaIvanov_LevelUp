package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

//входящие
public class InboxPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class,"
            + " 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myMail;
    @FindBy(xpath = "//span[@title='lvlup_test@mail.ru']")
    private WebElement recipientMyMail;
    @FindBy(xpath = "//h2[@class='thread-subject']")
    private WebElement topicMyMail;
    @FindBy(xpath = "//div[@class='letter__body']")
    private WebElement bodyMyMail;
    @FindBy(xpath = "//*[@id='app-canvas']"
            + "//span[@title='Удалить']")
    private WebElement deleteMyMail;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим в письмо во входящих")
    public void clickMyMail() {
        wait.until(ExpectedConditions
                .elementToBeClickable(myMail)).click();
    }

    @Step("Удаляем письмо из входящих")
    public void clickDeleteMyMail() {
        wait.until(ExpectedConditions
                .elementToBeClickable(deleteMyMail)).click();
    }

    @Step("Получаем значение в поле Кому во входящих")
    public String getRecipientMyMailText() {
        return wait.until(ExpectedConditions
                .visibilityOf(recipientMyMail)).getAttribute("title");
    }

    @Step("Получаем значение в поле Тема во входящих")
    public String getTopicMyMailText() {
        return wait.until(ExpectedConditions
                .visibilityOf(topicMyMail)).getAttribute("textContent");
    }

    @Step("Получаем значение в поле Тело во входящих")
    public String getBodyMyMailText() {
        return wait.until(ExpectedConditions
                .visibilityOf(bodyMyMail)).getAttribute("innerText");
    }

    @Step("Ожидаем загрузки страницы с входящими")
    public void waitForPageLoaded() {
        super.waitForPageLoaded("Входящие");
    }
}
