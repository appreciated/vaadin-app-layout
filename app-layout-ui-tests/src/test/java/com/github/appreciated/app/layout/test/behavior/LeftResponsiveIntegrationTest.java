package com.github.appreciated.app.layout.test.behavior;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.behavior.leftresponsive.LeftResponsiveBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LeftResponsiveIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement root = getShadowRootElement("app-layout-left-responsive");
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
        return LeftResponsiveBehaviourView.class;
    }
}
