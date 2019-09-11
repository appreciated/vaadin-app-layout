package com.github.appreciated.app.layout.test.addon;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.addon.notification.NotificationView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ProfileTest extends BaseTest {

    @Test
    public void testMenu() {
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 0);
        clickByCssSelector(".app-menu-item", 1);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 2);
        clickByCssSelector(".app-menu-item", 3);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 4);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 5);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 6);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 7);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 8);
        clickByID(getElementRoot(), "#toggle");
        clickByCssSelector(".app-menu-item", 9);
    }

    @Override
    public Class getRoutePrefixClass() {
        return NotificationView.class;
    }

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
}
