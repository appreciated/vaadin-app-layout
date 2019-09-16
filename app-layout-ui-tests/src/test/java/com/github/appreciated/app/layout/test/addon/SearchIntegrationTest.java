package com.github.appreciated.app.layout.test.addon;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.addon.search.SearchView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SearchIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        openSearch();
        closeSearch();
        openSearch();
        Selenide.$$("vaadin-vertical-layout").get(5).click();
        $(byText("Header1 clicked")).should(Condition.visible);
        sleep(1000);
    }

    private void openSearch() {
        Selenide.$("#it-test-search-field").shouldNot(Condition.visible);
        Selenide.$("#it-test-search-button").click();
        sleep(1000);
        Selenide.$("#it-test-search-field").should(Condition.visible);
        Selenide.executeJavaScript("document.getElementById(\"it-test-search-field\").value = \"Header\"");
        sleep(1000);
        $(byText("Header1")).should(Condition.visible);
        $(byText("Description1")).should(Condition.visible);
    }

    private void closeSearch() {
        Selenide.$("#it-test-search-close").click();
        $(byText("Header1")).shouldNot(Condition.visible);
        $(byText("Description1")).shouldNot(Condition.visible);
    }

    @Override
    public Class getRoutePrefixClass() {
        return SearchView.class;
    }

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
}
