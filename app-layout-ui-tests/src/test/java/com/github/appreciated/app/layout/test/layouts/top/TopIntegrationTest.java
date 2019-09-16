package com.github.appreciated.app.layout.test.layouts.top;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.layouts.top.top.TopBehaviourView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TopIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        WebElement root = getShadowRootElement("app-layout-top");
        clickByCssSelector("paper-tab", 0);
        clickByCssSelector("paper-tab", 1);
        clickByCssSelector("paper-tab", 2);
    }

    @Override
    public Class getRoutePrefixClass() {
        return TopBehaviourView.class;
    }
}
