package com.framework.pages.desktop;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NotepadPage extends BasePage {

    /**
     * Editor locators differ between Notepad versions, so we try them in order:
     *  - Windows 11 Notepad : class RichEditD2DPT
     *  - Windows 10 Notepad : class Edit
     * Use Inspect.exe (Windows SDK) or Appium Desktop Inspector to confirm on your machine.
     */
    private static final By[] EDITOR_LOCATORS = {
            By.className("RichEditD2DPT"),
            By.className("Edit"),
            By.name("Text Editor")
    };

    private WebElement getEditor() {
        return wait.until(d -> {
            for (By locator : EDITOR_LOCATORS) {
                List<WebElement> found = d.findElements(locator);
                if (!found.isEmpty()) {
                    return found.get(0);
                }
            }
            return null;
        });
    }

    public NotepadPage typeText(String text) {
        getEditor().sendKeys(text);
        return this;
    }

    public String getText() {
        return getEditor().getText();
    }
}
