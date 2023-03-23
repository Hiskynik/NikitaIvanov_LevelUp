package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DraftsPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]")
    private WebElement myDraft;

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public void clickMyDraft() {
        wait.until(ExpectedConditions.elementToBeClickable(myDraft)).click();
    }


}
