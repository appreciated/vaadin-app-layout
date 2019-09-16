package com.github.appreciated.app.layout.test.layouts.left;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.layouts.left.leftoverlay.LeftOverlayBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftOverlayIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement elementRoot = getShadowRootElement("app-layout-left-overlay");
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 0);
        clickByCssSelector(".app-menu-item", 1);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 2);
        clickByCssSelector(".app-menu-item", 3);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 4);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 5);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 6);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 7);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 8);
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 9);
    }

    @Override
    public Class getRoutePrefixClass() {
        return LeftOverlayBehaviourView.class;
    }
}
