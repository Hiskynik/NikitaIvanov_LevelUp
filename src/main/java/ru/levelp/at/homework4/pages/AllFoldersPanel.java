package ru.levelp.at.homework4.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AllFoldersPanel {

    @FindBy(xpath = "//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")
    private WebElement testButton;


    private final WebDriver driver;
    private final WebDriverWait wait;


    public AllFoldersPanel(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void clickTestButton() {
        wait.until(ExpectedConditions.elementToBeClickable(testButton)).click();
    }


}
