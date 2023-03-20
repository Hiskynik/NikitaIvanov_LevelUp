package ru.levelp.at.homework4.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
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
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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

    public void fillEmailTextField(final String email) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(email);
    }

    public void clickPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordButton)).click();
    }

    public void fillPasswordTextField(final String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(password);

    }

    public void clickEntranceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(entranceButton)).click();


    }
}
