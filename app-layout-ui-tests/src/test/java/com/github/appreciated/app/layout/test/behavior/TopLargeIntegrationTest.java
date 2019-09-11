package com.github.appreciated.app.layout.test.behavior;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.behavior.toplarge.TopLargeBehaviourView;
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
    public Class getRoutePrefixClass() {
        return TopLargeBehaviourView.class;
    }
}
