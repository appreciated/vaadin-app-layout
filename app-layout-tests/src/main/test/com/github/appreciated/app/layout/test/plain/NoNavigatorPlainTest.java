package com.github.appreciated.app.layout.test.plain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.appreciated.app.layout.test.ui.plain.NoNavigatorPlainUI;
import com.github.appreciated.app.layout.test.view.plain.basic.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoNavigatorPlainUI.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoNavigatorPlainTest {


    @LocalServerPort
    int randomServerPort;

    private static WebDriver driver;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "selenium/bin/windows/googlechrome/64bit/chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @Test
    public void testMenu() {
        openWebsite();

        $(withText(View1.class.getName())).should(Condition.visible);

        clickElement($(byText("My Submenu")));
        clickElement($(byText("Charts")));
        $(withText(View2.class.getName())).should(Condition.visible);

        clickElement($(byText("Contact")));
        $(withText(View3.class.getName())).should(Condition.visible);

        clickElement($(byText("More")));
        $(withText(View4.class.getName())).should(Condition.visible);

        clickElement($(byText("Menu")));
        $(withText(View5.class.getName())).should(Condition.visible);

        clickElement($(byText("Elements")));
        $(withText(View6.class.getName())).should(Condition.visible);
    }

    private void clickElement(SelenideElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openWebsite() {
        open("http://localhost:" + randomServerPort);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}