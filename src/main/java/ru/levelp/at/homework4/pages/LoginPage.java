package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class,'resplash-btn')]")
    private WebElement loginButton;
    @FindBy(xpath = "//*[contains(@class,'ag-popup__frame__layout__iframe')]")
    private WebElement frameElement;
    @FindBy(xpath = "//*[@id='login-content']//input[@name='username']")
    private WebElement emailTextField;
    @FindBy(xpath = "//*[contains(@class,'submit-button-wrap')]")
    private WebElement passwordButton;
    @FindBy(xpath = "//*[@id='login-content']//input[@name='password']")
    private WebElement passwordTextField;
    @FindBy(xpath = "//*[contains(@class,'submit-button')]")
    private WebElement entranceButton;

    private static final String MAIL_URL = "https://mail.ru/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(MAIL_URL);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void switchLoginFrame() {
        driver.switchTo().frame(frameElement);
    }

    public void fillEmailTextField(final String emailname) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(emailname);
    }

    public void clickPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordButton)).click();
    }

    public void fillPasswordTextField(final String emailpassword) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(emailpassword);

    }

    public void clickEntranceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(entranceButton)).click();


    }
}
