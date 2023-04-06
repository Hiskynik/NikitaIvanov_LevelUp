package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class IndexPageEmail extends BasePage {
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
    @FindBy(xpath = "//*[@id='sideBarContent']//a[@data-folder-link-id='500002']")
    private WebElement basketButton;
    @FindBy(xpath = "//*[@id='ph-whiteline']//div[@data-testid='whiteline-account']")
    private WebElement profileButton;



    public IndexPageEmail(WebDriver driver) {
        super(driver);
    }

    public void clickNewLetterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newLetterButton)).click();
    }

    public void clickAllFoldersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(allFoldersButton)).click();
    }

    public void clickIncomingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(incomingButton)).click();
    }

    public void clickSentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sentButton)).click();
    }

    public void clickDraftsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(draftsButton)).click();
    }

    public void clickBasketButton() {
        wait.until(ExpectedConditions.elementToBeClickable(basketButton)).click();
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }
}
