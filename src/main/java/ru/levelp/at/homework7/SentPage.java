package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class SentPage extends BasePage {

    public SentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидаем загрузку страницы с отправленными")
    public void waitForPageLoaded() {
        super.waitForPageLoaded("Отправленные");
    }
}

