package ru.levelp.at.homework7;

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.levelp.at.homework7.listener.AllureAttachmentCallback;
import ru.levelp.at.homework7.listener.AllureAttachmentReport;
//Базовый тест

@ExtendWith({AllureAttachmentReport.class, AllureAttachmentCallback.class})
class BaseMailTest {

    private static final String URL = "https://google.com";
    private static final String MAIL = "https://mail.ru";
    protected WebDriver driver;

    @BeforeEach
    void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(URL);
        driver.navigate().to(MAIL);
        TestContext.getInstance().addObject("driver", driver);


    }

    @AfterEach
    void tearDown() {
        driver.quit();
        TestContext.clear();


    }
}
