package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//профиль
public class ProfilePanel extends BasePage {

    @FindBy(xpath = "//div[@data-click-counter='75068944']")
    private WebElement exitButton;


    public ProfilePanel(WebDriver driver) {
        super(driver);
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
