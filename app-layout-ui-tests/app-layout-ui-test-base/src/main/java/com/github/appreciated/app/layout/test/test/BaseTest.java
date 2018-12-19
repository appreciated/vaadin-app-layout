package com.github.appreciated.app.layout.test.test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {
    public static WebDriver driver;
    public static int SLEEP = 400;

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

    public void clickByCssSelector(String cssSelector, int i) {
        $$(cssSelector).get(i).click();
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickByID(WebElement element, String id) {
        findByID(element, id).click();
        try {
            Thread.sleep(SLEEP);
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

    public void clickByCssSelector(WebElement element, String className, int i) {
        findByClassName(element, className, i).click();
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void clickElement(SelenideElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openWebsite() {
        open("http://localhost:" + getServerPort());
    }

    public abstract int getServerPort();
}
