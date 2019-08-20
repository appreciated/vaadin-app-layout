package com.github.appreciated.app.layout.test;

import com.github.appreciated.app.layout.test.nestedlayout.AccessDeniedExceptionHandler;
import com.github.appreciated.app.layout.test.nestedlayout.NestedLayout;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class NestedLayoutIntegrationTest extends BaseTest {

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
        String message = getTextOfElementByCssSelector("#" + AccessDeniedExceptionHandler.ID, 0);
        Assert.assertEquals(AccessDeniedExceptionHandler.ERROR_MESSAGE, message);
    }

    @Override
    Class getRoutePrefixClass() {
        return NestedLayout.class;
    }
}
