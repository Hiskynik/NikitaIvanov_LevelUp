package ru.levelp.at.homework4.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SentPage {

    @FindBy(xpath = "")
    private WebElement sentTitle;
    @FindBy(xpath = "//*[@id='app-canvas']//div[@class='llc__background']")
    private List<WebElement> sentMails;

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    //   WebDriverWait waitSendingEmails2 = new WebDriverWait(driver, Duration.ofSeconds(50));
    //     waitSendingEmails2.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));

    //public List<WebElement> sentMails = driver.findElements(By
    //       .xpath("//*[@id='app-canvas']//div[@class='llc__background']"));*/
    public void clickLoginButton() {
        wait.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
    }


}
