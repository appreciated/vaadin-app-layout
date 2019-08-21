package com.github.appreciated.app.layout.navigation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class RouteSimilarityTest {
    @Test
    public void testRouteSimilarity() {
        String[] urls = new String[]{
                "navigation/view1",
                "navigation/view2",
                "navigation/view3",
                "navigation/view4",
                "navigation/view5",
                "navigation/view6",
                "navigation/view7",
                "navigation/view8",
                "navigation/view9",
                "navigation"
        };

        String currentRoutePart = "navigation";

        RouteSimilarity r = Arrays.stream(urls)
                .map(s -> new RouteSimilarity(currentRoutePart, s))
                .max(Comparator.comparingInt(RouteSimilarity::getSimilarity)).get();
        String result2 = urls[9];

        Assert.assertEquals(r.getRouteString(), result2);
    }

    @Test
    public void testRouteSimilarity2() {
        String[] urls = new String[]{
                "view1",
                "view2",
                "view3",
                "view4",
                "view5",
                "view6",
                "view7",
                "view8",
                "view9",
                ""
        };

        String currentRoutePart = "";

        RouteSimilarity r = Arrays.stream(urls)
                .map(s -> new RouteSimilarity(currentRoutePart, s))
                .max(Comparator.comparingInt(RouteSimilarity::getSimilarity)).get();
        String result1 = r.getRouteString();
        String result2 = urls[9];

        Assert.assertEquals(result1, result2);
    }

    @Test
    public void testRouteSimilarity3() {
        String[] urls = new String[]{
                "navigation/view2",
                "navigation2/view1",
                "navigation/view1",
                "navigation/view4",
                "navigation/view5",
                "navigation/view6",
                "navigation/view7",
                "navigation/view8",
                "navigation/view9",
                "navigation",
        };

        String currentRoutePart = "navigation/view1";

        RouteSimilarity r = Arrays.stream(urls)
                .map(s -> new RouteSimilarity(currentRoutePart, s))
                .max(Comparator.comparingInt(RouteSimilarity::getSimilarity)).get();
        String result1 = r.getRouteString();
        String result2 = urls[2];

        Assert.assertEquals(result1, result2);
    }

}