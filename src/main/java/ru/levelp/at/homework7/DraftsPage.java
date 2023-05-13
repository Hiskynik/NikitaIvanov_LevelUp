package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

//черновик
public class DraftsPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class,"
            +
            " 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myDraft;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            +
            "//span[@class='text--1tHKB']")
    private WebElement recipientField;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            +
            "//input[@name='Subject']")
    private WebElement topicField;
    @FindBy(xpath = "//div[@class='compose-app__compose']"
            +
            "//div[@role='textbox']")
    private WebElement bodyField;

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем чеерновик")
    public void clickMyDraft() {
        wait.until(ExpectedConditions
                .elementToBeClickable(myDraft)).click();
    }

    @Step("Получаем значение в поле Кому")
    public String getRecipientFieldText() {
        return wait.until(ExpectedConditions
                .visibilityOf(recipientField)).getAttribute("innerText");
    }

    @Step("Получаем значение в поле Тема письма")
    public String getTopicFieldText() {
        return wait.until(ExpectedConditions
                .visibilityOf(topicField)).getAttribute("value");
    }

    @Step("Получаем значение в поле Тело письма")
    public String getBodyFieldText() {
        return wait.until(ExpectedConditions
                .visibilityOf(bodyField)).getAttribute("innerText");
    }

    @Step("Ожидаем загрузку страницы с черновиками")
    public void waitForPageLoaded() {
        super.waitForPageLoaded("Черновики");
    }
}
