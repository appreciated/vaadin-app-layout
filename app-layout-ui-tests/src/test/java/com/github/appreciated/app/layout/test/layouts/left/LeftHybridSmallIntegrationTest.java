package com.github.appreciated.app.layout.test.layouts.left;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.layouts.left.lefthybridsmall.LeftHybridSmallBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftHybridSmallIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement elementRoot = getShadowRootElement("app-layout-left-hybrid");
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
    public Class getRoutePrefixClass() {
        return LeftHybridSmallBehaviourView.class;
    }
}
