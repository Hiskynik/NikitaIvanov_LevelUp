package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class AllFoldersPanel extends BasePage {

    @FindBy(xpath = "//div[@class='sidebar__full fn-enter']"
            + "//a[@class = 'nav__item nav__item_expanded_true nav__item_child-level_0']")
    private WebElement testButton;


    public AllFoldersPanel(WebDriver driver) {
        super(driver);
    }


    public void clickTestButton() {
        wait.until(ExpectedConditions.elementToBeClickable(testButton)).click();
    }


}
