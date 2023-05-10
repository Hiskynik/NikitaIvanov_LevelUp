package ru.levelp.at.homework7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.levelp.at.homework7.listener.AllureAttachmentCallback;
import ru.levelp.at.homework7.listener.AllureAttachmentReport;

import java.io.IOException;

@ExtendWith({AllureAttachmentReport.class, AllureAttachmentCallback.class})
class BaseMailTest {

    private static final String url = "https://google.com";
    private static final String mail = "https://mail.ru";
    protected WebDriver driver;

    @BeforeEach
    void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to(url);
        driver.navigate().to(mail);
        TestContext.getInstance().addObject("driver", driver);


    }

    @AfterEach
    void tearDown() {
        driver.quit();
        TestContext.clear();


    }
}
