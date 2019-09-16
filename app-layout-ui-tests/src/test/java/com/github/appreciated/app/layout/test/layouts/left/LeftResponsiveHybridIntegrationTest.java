package com.github.appreciated.app.layout.test.layouts.left;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.layouts.left.leftresponsivehybrid.LeftResponsiveHybridBehaviourView;
import org.junit.Test;

public class LeftResponsiveHybridIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
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
        return LeftResponsiveHybridBehaviourView.class;
    }
}
