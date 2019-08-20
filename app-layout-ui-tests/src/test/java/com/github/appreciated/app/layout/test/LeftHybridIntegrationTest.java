package com.github.appreciated.app.layout.test;


import com.github.appreciated.app.layout.test.lefthybrid.LeftHybridBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftHybridIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement elementRoot = getShadowRootElement("app-layout-left-hybrid");
        clickByID(elementRoot, "#toggle");
        clickByID(elementRoot, "#toggle");
        clickByCssSelector(".app-menu-item", 0);
        clickByCssSelector(".app-menu-item", 1);
        clickByCssSelector(".app-menu-item", 2);
        clickByCssSelector(".app-menu-item", 3);
        clickByCssSelector(".app-menu-item", 4);
        clickByCssSelector(".app-menu-item", 5);
        clickByCssSelector(".app-menu-item", 6);
        clickByCssSelector(".app-menu-item", 7);
        clickByCssSelector(".app-menu-item", 8);
        clickByCssSelector(".app-menu-item", 9);
    }

    @Override
    Class getRoutePrefixClass() {
        return LeftHybridBehaviourView.class;
    }
}
