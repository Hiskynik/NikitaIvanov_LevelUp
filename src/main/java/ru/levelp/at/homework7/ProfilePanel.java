package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/ProfilePanel.java
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePanel {

    @FindBy(xpath = "//div[@data-click-counter='75068944']")
    private WebElement exitButton;

    private final WebDriver driver;
    private final WebDriverWait wait;
=======


public class ProfilePanel extends BasePage {

    @FindBy(xpath = "//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")
    private WebElement exitButton;
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/ProfilePanel.java

    public ProfilePanel(WebDriver driver) {
<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/ProfilePanel.java
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
=======
        super(driver);
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/ProfilePanel.java
    }

    @Step("Выходим из почты")
    public void clickExitButton() {
<<<<<<< HEAD:src/main/java/ru/levelp/at/homework7/ProfilePanel.java
        wait.until(ExpectedConditions
                .elementToBeClickable(exitButton)).click();
=======
        wait.until(ExpectedConditions.elementToBeClickable(exitButton)).click();
>>>>>>> parent of 49a5780 (Homework #7 (rebase #4)):src/main/java/ru/levelp/at/homework4/pages/ProfilePanel.java
    }

    public WebDriver getDriver() {
        return driver;
    }
}
