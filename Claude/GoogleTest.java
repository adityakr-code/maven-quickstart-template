package com.framework.web;

import com.framework.base.BaseWebTest;
import com.framework.pages.web.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseWebTest {

    @Test(groups = "web")
    public void googleHomePageLoads() {
        GoogleHomePage google = new GoogleHomePage().open();

        Assert.assertTrue(google.getTitle().contains("Google"), "Title should contain 'Google'");
        Assert.assertTrue(google.isSearchBoxDisplayed(), "Search box should be visible");
    }

    @Test(groups = "web")
    public void searchShowsResultsPage() {
        GoogleHomePage google = new GoogleHomePage().open();
        google.search("Selenium Java");

        // Note: Google may show a CAPTCHA to automated browsers; if so this assertion can fail.
        Assert.assertTrue(google.getCurrentUrl().contains("q="), "URL should contain the search query");
    }
}
