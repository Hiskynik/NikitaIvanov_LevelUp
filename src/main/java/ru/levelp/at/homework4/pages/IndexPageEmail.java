package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class IndexPageEmail {

    @FindBy(xpath = "//*[@id='app-canvas']//span[@class='compose-button__wrapper']")
    private WebElement NewLetterButton;
    @FindBy(xpath = "//*[@class = 'sidebar__header']//a[@title = 'Все папки']")
    private WebElement AllFoldersButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Входящие')]")
    private WebElement IncomingButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]")
    private WebElement SentButton;
    @FindBy(xpath = "//*[contains(@class, 'nav__item') and contains(.,'Черновики')]")
    private WebElement DraftsButton;
    @FindBy(xpath = "//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")
    private WebElement BasketButton;
    @FindBy(xpath = "//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")
    private WebElement ProfileButton;


    private final WebDriver driver;
    private final WebDriverWait wait;


    public IndexPageEmail(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickNewLetterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(NewLetterButton)).click();
    }

    public void clickAllFoldersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(AllFoldersButton)).click();
    }

    public void clickIncomingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(IncomingButton)).click();
    }

    public void clickSentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SentButton)).click();
    }

    public void clickDraftsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(DraftsButton)).click();
    }

    public void clickBasketButton() {
        wait.until(ExpectedConditions.elementToBeClickable(BasketButton)).click();
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ProfileButton)).click();
    }
}