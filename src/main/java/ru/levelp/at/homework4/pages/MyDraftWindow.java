package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MyDraftWindow extends BasePage {
    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@type='text']")
    private static WebElement RecipientField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@name='Subject']")
    private static WebElement TopicField;
    @FindBy(xpath = "//div[@class='compose-app__compose']//div[@role='textbox']")
    private static WebElement BodyField;

    public MyDraftWindow(WebDriver driver) {
        super(driver);
    }

    public static String getRecipientFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(RecipientField)).getText();
    }

    public static String getTopicFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(TopicField)).getText();
    }

    public static String getBodyFieldText() {
        return wait.until(ExpectedConditions.visibilityOf(BodyField)).getText();
    }
}
