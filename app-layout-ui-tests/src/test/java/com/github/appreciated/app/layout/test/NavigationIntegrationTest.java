package com.github.appreciated.app.layout.test;

import com.codeborne.selenide.Condition;
import com.github.appreciated.app.layout.test.navigation.NavigationView;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$$;

public class NavigationIntegrationTest extends BaseTest {


    @Test
    public void testHighlight() {

        openWebsite("view1");
        $$(".app-menu-item").get(1).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(2).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(3).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(4).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(5).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(6).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(7).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(8).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(9).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(10).shouldNotHave(Condition.attribute("highlight"));

        openWebsite("view2");
        $$(".app-menu-item").get(1).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(2).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(3).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(4).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(5).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(6).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(7).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(8).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(9).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(10).shouldNotHave(Condition.attribute("highlight"));

        openWebsite("view1");
        $$(".app-menu-item").get(1).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(2).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(3).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(4).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(5).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(6).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(7).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(8).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(9).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(10).shouldNotHave(Condition.attribute("highlight"));

        openWebsite("view2-alias");
        $$(".app-menu-item").get(1).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(2).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(3).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(4).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(5).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(6).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(7).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(8).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(9).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(10).shouldNotHave(Condition.attribute("highlight"));

        openWebsite("view2/subcontent");
        $$(".app-menu-item").get(1).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(2).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(3).shouldHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(4).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(5).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(6).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(7).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(8).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(9).shouldNotHave(Condition.attribute("highlight"));
        $$(".app-menu-item").get(10).shouldNotHave(Condition.attribute("highlight"));

        WebElement elementRoot = getShadowRootElement("app-layout-left-hybrid");
        //Assert.assertEquals("arrow-back", findByID(elementRoot, "#toggle").getAttribute("icon"));
        clickByID(elementRoot, "#toggle");
        //Assert.assertEquals("menu", findByID(elementRoot, "#toggle").getAttribute("icon"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(getWebsiteUrl() + "/view2", getDriver().getCurrentUrl());
    }

    @Override
    Class getRoutePrefixClass() {
        return NavigationView.class;
    }
}
