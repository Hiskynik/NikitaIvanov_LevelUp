package ru.levelp.at.homework7.listener;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.homework7.TestContext;

public class AllureAttachmentCallback implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        System.out.println("afterTestExecution");
        if (extensionContext.getExecutionException().isPresent()) {
            System.out.println("Делаем скриншот");
            attach();
        }
    }

    private void attach() {
        final WebDriver driver = TestContext.getInstance().getObject("driver");
        attachScreenShot(driver);
        attachPageSource(driver);
    }

    @Attachment(type = "image/png", fileExtension = ".png")
    private byte[] attachScreenShot(final WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void attachPageSource(final WebDriver driver) {
        var ps = driver.getPageSource();
        Allure.addAttachment("page_source_code", "text/html", ps, ".html");
    }
}

