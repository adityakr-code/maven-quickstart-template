package com.framework.pages.web;

import com.framework.config.ConfigReader;
import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleHomePage extends BasePage {

    private final By searchBox = By.name("q");

    public GoogleHomePage open() {
        driver.get(ConfigReader.get("web.url"));
        return this;
    }

    public boolean isSearchBoxDisplayed() {
        return waitForVisible(searchBox).isDisplayed();
    }

    public void search(String text) {
        type(searchBox, text);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
