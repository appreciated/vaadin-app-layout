package com.github.appreciated.app.layout.test.annotation;


import com.github.appreciated.app.layout.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class AnnotationIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement root = getShadowRootElement("app-layout-left-responsive");
        clickByCssSelector(".app-menu-item", 0);
        clickByCssSelector(".app-menu-item", 1);
        Assert.assertEquals("Abacus", getTextByCssSelector(".app-menu-item", 1));
        clickByCssSelector(".app-menu-item", 2);
        Assert.assertEquals("My Submenu", getTextByCssSelector(".app-menu-item", 2));
        clickByCssSelector(".app-menu-item", 3);
        Assert.assertEquals("Backspace", getTextByCssSelector(".app-menu-item", 3));
        clickByCssSelector(".app-menu-item", 4);
        Assert.assertEquals("Calc", getTextByCssSelector(".app-menu-item", 4));
        clickByCssSelector(".app-menu-item", 5);
        Assert.assertEquals("Dashboard", getTextByCssSelector(".app-menu-item", 5));
        clickByCssSelector(".app-menu-item", 6);
        Assert.assertEquals("Edit", getTextByCssSelector(".app-menu-item", 6));
        clickByCssSelector(".app-menu-item", 7);
        Assert.assertEquals("Facebook", getTextByCssSelector(".app-menu-item", 7));
        clickByCssSelector(".app-menu-item", 8);
        Assert.assertEquals("Gamepad", getTextByCssSelector(".app-menu-item", 8));
        clickByCssSelector(".app-menu-item", 9);
        Assert.assertEquals("Hammer", getTextByCssSelector(".app-menu-item", 9));
        clickByCssSelector(".app-menu-item", 10);
        Assert.assertEquals("Inbox", getTextByCssSelector(".app-menu-item", 10));
    }

    @Override
    public Class getRoutePrefixClass() {
        return AnnotationView.class;
    }
}
