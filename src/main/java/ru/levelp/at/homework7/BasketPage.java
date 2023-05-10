package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидаем загрузки страницы с удаленными письмами")
    public void waitForPageLoaded() {
        super.waitForPageLoaded("Корзина");

    }
}
