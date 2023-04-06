package ru.levelp.at.homework3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class BaseMailTest {
    private static final String url = "https://google.com";
    private static final String mail = "https://mail.ru";
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(url);
        driver.navigate().to(mail);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
