package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class, "
            + "'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myTestMail;
    @FindBy(xpath = "//span[@title='lvlup_test@mail.ru']")
    private WebElement recipientTest;
    @FindBy(xpath = "//h2[@class='thread-subject']")
    private WebElement topicTest;
    @FindBy(xpath = "//div[@class='letter__body']")
    private WebElement bodyTest;

    public TestPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем письмо в папке Тест")
    public void clickMyTestMail() {
        wait.until(ExpectedConditions
                .elementToBeClickable(myTestMail)).click();
    }

    @Step("Получаем значение в поле Кому в папке Тест")
    public String getRecipientTestText() {
        return wait.until(ExpectedConditions
                .visibilityOf(recipientTest)).getAttribute("title");
    }

    @Step("Получаем значение в поле Тема в папке Тест")
    public String getTopicTestText() {
        return wait.until(ExpectedConditions
                .visibilityOf(topicTest)).getAttribute("textContent");
    }

    @Step("Получаем значение в поле Тело в папке Тест")
    public String getBodyTestText() {
        return wait.until(ExpectedConditions
                .visibilityOf(bodyTest)).getAttribute("innerText");
    }


    @Step("Ожидаем загрузки страницы в папке Тест")
    public void waitForPageLoaded() {
        super.waitForPageLoaded("Test");
    }
}
