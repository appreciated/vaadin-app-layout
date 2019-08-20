package com.github.appreciated.app.layout.test;


import com.github.appreciated.app.layout.test.left.LeftBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement elementRoot = getShadowRootElement("app-layout-left");
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
    Class getRoutePrefixClass() {
        return LeftBehaviourView.class;
    }
}
