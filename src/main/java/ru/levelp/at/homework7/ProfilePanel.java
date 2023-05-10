package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProfilePanel {

    @FindBy(xpath = "//div[@data-click-counter='75068944']")
    private WebElement exitButton;


    private final WebDriver driver;
    private final WebDriverWait wait;


    public ProfilePanel(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Выходим из почты")
    public void clickExitButton() {
        wait.until(ExpectedConditions
                .elementToBeClickable(exitButton)).click();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
