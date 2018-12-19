package com.github.appreciated.app.layout.test.annotated;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.appreciated.app.layout.test.test.BaseTest;
import com.github.appreciated.app.layout.test.top.TopMain;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopTest extends BaseTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testMenu() {
        openWebsite();
        WebElement root = getShadowRootElement("app-layout-top");
        clickByCssSelector("paper-tab", 0);
        clickByCssSelector("paper-tab", 1);
        clickByCssSelector("paper-tab", 2);
    }

    @Override
    public int getServerPort() {
        return randomServerPort;
    }
}
