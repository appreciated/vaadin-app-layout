package com.github.appreciated.app.layout.navigation;

import org.junit.Assert;
import org.junit.Test;

public class UpNavigationHelperTest {

    @Test
    public void getClosestRoute() {
        String[] urls = new String[]{
                "navigation/view1",
                "navigation/view2",
                "navigation/view2/subview",
                "navigation/view3",
                "navigation/view4",
                "navigation/view5",
                "navigation/view6",
                "navigation/view7",
                "navigation/view8",
                "navigation/view9",
                "navigation"
        };

        String currentRoutePart = "navigation/view2/subview";

        RouteSimilarity res = UpNavigationHelper.getClosestRoute(currentRoutePart, urls).get();
        String ress = res.getRouteString();
        Assert.assertEquals(res.getRouteString(), "navigation/view2");
    }
}