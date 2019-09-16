package com.github.appreciated.app.layout.test.addon;


import com.codeborne.selenide.Condition;
import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.addon.notification.NotificationView;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NotificationIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        sleep(3000);
        Assert.assertEquals("5", getShadowRootElement("paper-badge").findElement(byId("badge-text")).getText());
        openMenu();
        sleep(500);
        closeMenu();
        sleep(500);
        openMenu();
        sleep(500);
        closeMenu();
        sleep(500);
        openMenu();
        sleep(1000);
        $$("vaadin-button").get(4).click();
        closeMenu();
        Assert.assertEquals("4", getShadowRootElement("paper-badge").findElement(byId("badge-text")).getText());
    }

    private void closeMenu() {
        $$("vaadin-button").get(2).click();
        $(byText("Header 1")).shouldNot(Condition.visible);
        $(byText("Description 1")).shouldNot(Condition.visible);
        $(byText("Header 2")).shouldNot(Condition.visible);
        $(byText("Description 2")).shouldNot(Condition.visible);
        $(byText("Header 3")).shouldNot(Condition.visible);
        $(byText("Description 3")).shouldNot(Condition.visible);
        $(byText("Header 4")).shouldNot(Condition.visible);
        $(byText("Description 4")).shouldNot(Condition.visible);
        $(byText("Header 5")).shouldNot(Condition.visible);
        $(byText("Description 5")).shouldNot(Condition.visible);
    }

    private void openMenu() {
        $("#it-test-notification-button").click();
        sleep(1000);
        $(byText("Header 1")).should(Condition.visible);
        $(byText("Description 1")).should(Condition.visible);
        $(byText("Header 2")).should(Condition.visible);
        $(byText("Description 2")).should(Condition.visible);
        $(byText("Header 3")).should(Condition.visible);
        $(byText("Description 3")).should(Condition.visible);
        $(byText("Header 4")).should(Condition.visible);
        $(byText("Description 4")).should(Condition.visible);
        $(byText("Header 5")).should(Condition.visible);
        $(byText("Description 5")).should(Condition.visible);
    }

    @Override
    public Class getRoutePrefixClass() {
        return NotificationView.class;
    }

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
}
