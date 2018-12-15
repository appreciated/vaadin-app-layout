package com.github.appreciated.app.layout.test.annotated;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.appreciated.app.layout.test.lefthybrid.LeftHybridMain;
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
@SpringBootTest(classes = LeftHybridMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeftHybridTest {


    private static WebDriver driver;
    @LocalServerPort
    int randomServerPort;

    @BeforeClass
    public static void init() {
        try (Stream<Path> stream = Files.find(Paths.get("../selenium/bin"), 15,
                (path, attr) -> (path.getFileName().toString().contains("chromedriver")))) {
            System.setProperty("webdriver.chrome.driver", stream.findFirst().get().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testMenu() {
        openWebsite();

        WebElement elementRoot = getShadowRootElement("app-layout-left");
        WebElement drawerRoot = getShadowRootDrawer("app-layout-left");
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 0);
        clickByClassName(drawerRoot, ".app-menu-item", 1);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 2);
        clickByClassName(drawerRoot, ".app-menu-item", 3);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 4);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 5);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 6);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 7);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 8);
        clickByID(elementRoot, "#toggle");
        clickByClassName(drawerRoot, ".app-menu-item", 9);
    }


// document.getElementsByTagName("app-layout-left")[0].shadowRoot.querySelector("app-drawer").querySelector("app-menu-icon-item")

    /**
     * document.getElementsByTagName("app-layout-left-responsive")[0].shadowRoot.childNodes[2].getElementsByTagName("app-drawer-layout")[0].getElementsByTagName("app-drawer")
     *
     * @param element
     * @return
     */
    public WebElement getShadowRootDrawer(String element) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return document.getElementsByTagName(\"" + element + "\")[0].shadowRoot.querySelector(\"app-drawer\")");
    }

    public WebElement getShadowRootElement(String element) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return document.getElementsByTagName(\"" + element + "\")[0].shadowRoot");
    }

    public WebElement findByTagName(WebElement element, String tag) {
        return findByTagName(element, tag, 0);
    }

    public WebElement findByTagName(WebElement element, String tag, int i) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].getElementsByTagName(\"" + tag + "\")[" + i + "]", element);
    }


    public WebElement findByID(WebElement element, String tag) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].querySelector(\"" + tag + "\")", element);
    }

    public void clickByTagName(WebElement element, String tag, int i) {
        findByTagName(element, tag, i).click();
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clickByID(WebElement element, String id) {
        findByID(element, id).click();
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public WebElement findByClassName(WebElement element, String className) {
        return findByClassName(element, className, 0);
    }

    public WebElement findByClassName(WebElement element, String className, int i) {
        WebElement root = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].querySelectorAll(\"" + className + "\")[" + i + "]", element);
        return root;
    }

    public void clickByClassName(WebElement element, String className, int i) {
        findByClassName(element, className, i).click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
