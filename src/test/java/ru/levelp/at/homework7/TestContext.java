package ru.levelp.at.homework7;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

//тест контекст
public final class TestContext {

    private static TestContext instance;
    private final Map<String, WebDriver> context;

    private TestContext() {
        this.context = new HashMap<>();
    }

    public void addObject(final String key, final WebDriver o) {
        context.put(key, o);
    }

    public WebDriver getObject(final String key) {
        return context.get(key);
    }

    public static void clear() {
        instance = null;
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
