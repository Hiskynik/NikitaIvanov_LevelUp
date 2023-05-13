package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

//вход
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

    @Step("Открываем домашнюю страницу mail.ru")
    public void open() {
        driver.navigate().to(MAIL_URL);
    }

    @Step("Нажимаем кнопку логина")
    public void clickLoginButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(loginButton)).click();
    }

    @Step("Переключаемся на фрейм")
    public void switchLoginFrame() {
        driver.switchTo().frame(frameElement);
    }

    @Step("Заполняем адрес почты")
    public void fillEmailTextField(final String email) {
        wait.until(ExpectedConditions
                .visibilityOf(emailTextField)).sendKeys(email);
    }

    @Step("Нажимаем кнопку пароля")
    public void clickPasswordButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(passwordButton)).click();
    }

    @Step("Заполняем пароль")
    public void fillPasswordTextField(final String password) {
        wait.until(ExpectedConditions
                .visibilityOf(passwordTextField)).sendKeys(password);

    }

    @Step("Нажимаем кнопку входа")
    public void clickEntranceButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(entranceButton)).click();
    }
}
