package ru.levelp.at.homework7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public abstract class BasePage {
    protected final WebDriver driver;
    protected static WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    protected void waitForPageLoaded(String pageTitle) {
        wait.until(ExpectedConditions.titleContains(pageTitle));
    }
}
