package com.github.appreciated.app.layout.test.annotated;

import com.github.appreciated.app.layout.test.annotation.AnnotationMain;
import com.github.appreciated.app.layout.test.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnnotationMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnnotationTest extends BaseTest {

    @LocalServerPort
    int randomServerPort;

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
    public int getServerPort() {
        return randomServerPort;
    }

}
