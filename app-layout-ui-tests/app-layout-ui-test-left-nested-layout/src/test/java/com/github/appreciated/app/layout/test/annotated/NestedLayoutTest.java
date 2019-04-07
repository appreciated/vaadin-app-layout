package com.github.appreciated.app.layout.test.annotated;

import com.github.appreciated.app.layout.test.left.AccessDeniedExceptionHandler;
import com.github.appreciated.app.layout.test.left.NestedLayoutMain;
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
@SpringBootTest(classes = NestedLayoutMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NestedLayoutTest extends BaseTest {

    @LocalServerPort
    int randomServerPort;

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
    public int getServerPort() {
        return randomServerPort;
    }
}
