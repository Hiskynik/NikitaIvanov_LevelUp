package ru.levelp.at.homework7.listener;

import org.junit.jupiter.api.extension.TestWatcher;

public class AllureAttachmentReport implements TestWatcher {
  /*
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = TestContext.getInstance().getObject("driver");
          attach(driver);
    }

    private void attach(final WebDriver driver) {
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


    } */
}
