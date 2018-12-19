package com.github.appreciated.app.layout.test.annotated;

import com.github.appreciated.app.layout.test.left.LeftMain;
import com.github.appreciated.app.layout.test.test.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeftMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeftTest extends BaseTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testMenu() {
        openWebsite();
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
    public int getServerPort() {
        return randomServerPort;
    }
}
