package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProfilePanel {

    @FindBy(xpath = "//div[@class='ph-accounts svelte-1labzyv']//div[@data-testid='whiteline-account-exit']")
    private WebElement ExitButton;


    private final WebDriver driver;
    private final WebDriverWait wait;


    public ProfilePanel(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void clickExitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ExitButton)).click();
    }


}


/*
    public void PageLoaded() {
        super.wait(PageLoaded(index.page.title));


 */
