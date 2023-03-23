package ru.levelp.at.homework4.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SentPage extends BasePage {
    @FindBy(xpath = "")
    private WebElement sentTitle;
    @FindBy(xpath = "//*[@id='app-canvas']//div[@class='llc__background']")
    private List<WebElement> sentMails;

    public SentPage(WebDriver driver) {
        super(driver);
    }
    //   WebDriverWait waitSendingEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
    //     waitSendingEmails2.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));

    //public List<WebElement> sentMails = driver.findElements(By
    //       .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));*/
    public void sentTitle() {
        wait.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
    }
    //  public List<WebElement> sentMails() {
    //    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(), );

}
