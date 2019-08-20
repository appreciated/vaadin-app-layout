package com.github.appreciated.app.layout.test;


import com.github.appreciated.app.layout.test.toplarge.TopLargeBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TopLargeIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement root = getShadowRootElement("app-layout-top-large");
        clickByCssSelector("paper-tab", 0);
        clickByCssSelector("paper-tab", 1);
        clickByCssSelector("paper-tab", 2);
    }

    @Override
    Class getRoutePrefixClass() {
        return TopLargeBehaviourView.class;
    }
}
