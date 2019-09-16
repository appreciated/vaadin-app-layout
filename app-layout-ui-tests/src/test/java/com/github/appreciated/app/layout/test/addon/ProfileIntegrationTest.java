package com.github.appreciated.app.layout.test.addon;


import com.codeborne.selenide.Condition;
import com.github.appreciated.app.layout.test.BaseTest;
import com.github.appreciated.app.layout.test.addon.profile.ProfileView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ProfileIntegrationTest extends BaseTest {

    @Test
    public void testMenu() {
        sleep(3000);
        $(withText("Profile Entry 1")).shouldNot(Condition.visible);
        $(withText("Profile Entry 2")).shouldNot(Condition.visible);
        $(withText("Profile Entry 3")).shouldNot(Condition.visible);
        $("#it-test-profile-button").click();
        $(withText("Profile Entry 1")).should(Condition.visible);
        $(withText("Profile Entry 2")).should(Condition.visible);
        $(withText("Profile Entry 3")).should(Condition.visible);
        $(withText("Profile Entry 1")).click();
        $(withText("Profile Entry 1 clicked")).should(Condition.visible);
        sleep(1000);
    }

    @Override
    public Class getRoutePrefixClass() {
        return ProfileView.class;
    }

    WebElement getElementRoot() {
        return getShadowRootElement("app-layout-left");
    }
}
