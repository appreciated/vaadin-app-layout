package com.github.appreciated.app.layout.test.annotated;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;
import com.github.appreciated.app.layout.test.annotation.NavigationMain;
import com.github.appreciated.app.layout.test.test.BaseTest;
import com.vaadin.flow.component.UI;
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
@SpringBootTest(classes = NavigationMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NavigationTest extends BaseTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testHighlight() {
        openRelativeUrl("");
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

        openRelativeUrl("view2");
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

        openRelativeUrl("view1");
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

        openRelativeUrl("view2-alias");
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
    }

    @Override
    public int getServerPort() {
        return randomServerPort;
    }

    private void openRelativeUrl(String url) {
       open("http://localhost:" + getServerPort() + "/" + url);
    }
}
