package ru.levelp.at.homework7;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//базовая страница
public abstract class BasePage {
    protected final WebDriverWait wait;
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForPageLoaded(String pageTitle) {
        wait.until(ExpectedConditions.titleContains(pageTitle));
    }
}
