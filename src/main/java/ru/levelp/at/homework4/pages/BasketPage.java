package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasketPage extends BasePage {
    @FindBy(xpath = "//div[@title='Корзина']")
    private WebElement basketTitle;

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void getBasketTitleText() {
        wait.until(ExpectedConditions.visibilityOf(basketTitle)).getText();
    }
}
