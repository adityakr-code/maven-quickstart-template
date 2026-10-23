package com.framework.driver;

import com.framework.config.ConfigReader;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Single place that creates, stores and quits drivers.
 * ThreadLocal keeps it safe for parallel execution later.
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver initDriver(DriverType type) {
        WebDriver driver;
        switch (type) {
            case WEB:
                driver = createChromeDriver();
                break;
            case DESKTOP:
                driver = createWindowsDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported driver type: " + type);
        }
        DRIVER.set(driver);
        return driver;
    }

    /** Shared access point used by page objects and tests. */
    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialised. Call DriverFactory.initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }

    // ---------------------------------------------------------------- web
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        if (ConfigReader.getBoolean("web.headless")) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return new ChromeDriver(options);
    }

    // ------------------------------------------------------------ desktop
    private static WebDriver createWindowsDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Windows");
        caps.setCapability("deviceName", "WindowsPC");
        caps.setCapability("app", ConfigReader.get("desktop.app"));

        try {
            return new WindowsDriver<WindowsElement>(new URL(ConfigReader.get("desktop.server.url")), caps);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid desktop.server.url in config.properties", e);
        }
    }
}
