package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SentPage extends BasePage {
    @FindBy(xpath = "//div[@title='Отправленные']")
    private WebElement sentTitle;

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public void getSentTitleText() {
        wait.until(ExpectedConditions.visibilityOf(sentTitle)).getText();
    }
}
