package com.github.appreciated.app.layout.test.addon;


import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.addon.profile.ProfileView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ProfileIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
    }

    @Override
    public Class getRoutePrefixClass() {
        return ProfileView.class;
    }

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
}
