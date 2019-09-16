package com.github.appreciated.app.layout.test.layouts.left;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.layouts.left.left.LeftBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftIntegrationTest extends BaseTest {

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

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
    
    @Override
    public Class getRoutePrefixClass() {
        return LeftBehaviourView.class;
    }
}
