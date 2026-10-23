package com.framework.desktop;

import com.framework.base.BaseDesktopTest;
import com.framework.pages.desktop.NotepadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotepadTest extends BaseDesktopTest {

    @Test(groups = "desktop")
    public void canTypeInNotepad() {
        String message = "Hello from WinAppDriver";

        NotepadPage notepad = new NotepadPage();
        notepad.typeText(message);

        Assert.assertTrue(notepad.getText().contains(message), "Notepad should contain the typed text");
    }
}
