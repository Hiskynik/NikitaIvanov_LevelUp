package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProfilePanel extends BasePage {

    @FindBy(xpath = "//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")
    private WebElement exitButton;


    public ProfilePanel(WebDriver driver) {
        super(driver);
    }


    public void clickExitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exitButton)).click();
    }


}
