package ru.levelp.at.homework7;

<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/LoginPage.java
import io.qameta.allure.Step;
import java.time.Duration;
=======
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/LoginPage.java
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

<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/LoginPage.java
    @Step("Заполняем адрес почты")

    public void fillEmailTextField(final String email) {
        wait.until(ExpectedConditions
                .visibilityOf(emailTextField)).sendKeys(email);
=======
    public void fillEmailTextField(final String emailname) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(emailname);
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/LoginPage.java
    }

    @Step("Нажимаем кнопку пароля")
    public void clickPasswordButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(passwordButton)).click();
    }

<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/LoginPage.java
    @Step("Заполняем пароль")
    public void fillPasswordTextField(final String password) {
        wait.until(ExpectedConditions
                .visibilityOf(passwordTextField)).sendKeys(password);
=======
    public void fillPasswordTextField(final String emailpassword) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(emailpassword);
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/LoginPage.java

    }

    @Step("Нажимаем кнопку входа")
    public void clickEntranceButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(entranceButton)).click();
    }
}
