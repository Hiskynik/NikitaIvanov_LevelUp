package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageEmail {

    @FindBy(xpath = "//*[@id='app-canvas']//span[@class='compose-button__wrapper']")
    private WebElement newLetterButton;
    @FindBy(xpath = "//*[@class = 'sidebar__header']//a[@title = 'Все папки']")
    private WebElement allFoldersButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Входящие')]")
    private WebElement incomingButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]")
    private WebElement sentButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Черновики')]")
    private WebElement draftsButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Корзина')]")
    private WebElement basketButton;
    @FindBy(xpath = "//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")
    private WebElement profileButton;


    private final WebDriver driver;
    private final WebDriverWait wait;


    public IndexPageEmail(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Создаем новое письмо")
    public void clickNewLetterButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(newLetterButton)).click();
    }

    @Step("Переходим во все папки")
    public void clickAllFoldersButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(allFoldersButton)).click();
    }

    @Step("Переходим во Входящие")
    public void clickIncomingButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(incomingButton)).click();
    }

    @Step("Переходим в Отправленные")
    public void clickSentButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(sentButton)).click();
    }

    @Step("Переходим в Черновики")
    public void clickDraftsButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(draftsButton)).click();
    }

    @Step("Переходим в Корзину")
    public void clickBasketButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(basketButton)).click();
    }

    @Step("Переходим в панель профиля")
    public void clickProfileButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(profileButton)).click();
    }

    public WebDriver getDriver() {
        return driver;
    }
}

