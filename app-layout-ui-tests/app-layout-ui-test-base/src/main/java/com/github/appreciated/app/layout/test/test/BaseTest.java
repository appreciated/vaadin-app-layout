package com.github.appreciated.app.layout.test.test;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {
    public static WebDriver driver;
    public static int SLEEP = 400;

    @BeforeClass
    public static void init() {
        WebDriverManager.chromedriver().version("74.0.3729.6").setup();
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void beforeTest() {
        openWebsite();
    }

    public void openWebsite() {
        open(getWebsiteUrl());
    }

    public String getWebsiteUrl() {
        return "http://localhost:" + getServerPort();
    }

    public abstract int getServerPort();

    public void clickByCssSelector(String cssSelector, int i) {
        clickElement($$(cssSelector).get(i));
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", webElement);
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextByCssSelector(String cssSelector, int i) {
        return $$(cssSelector).get(i).$("label", 0).getText();
    }

    public String getTextOfElementByCssSelector(String cssSelector, int i) {
        return $$(cssSelector).get(i).getText();
    }

    public WebElement getShadowRootElement(String element) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return document.getElementsByTagName(\"" + element + "\")[0].shadowRoot");
    }

    public void clickByID(WebElement element, String id) {
        clickElement(findByID(element, id));
    }

    public WebElement findByID(WebElement element, String tag) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].querySelector(\"" + tag + "\")", element);
    }

    public void clickByCssSelector(WebElement element, String className, int i) {
        findByClassName(element, className, i).click();
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement findByClassName(WebElement element, String className, int i) {
        WebElement root = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].querySelectorAll(\"" + className + "\")[" + i + "]", element);
        return root;
    }

    public void openWebsite(String relativePath) {
        open(getWebsiteUrl() + "/" + relativePath);
    }


}
