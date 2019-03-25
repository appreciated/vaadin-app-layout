package com.github.appreciated.app.layout.router.navigation;

import com.vaadin.flow.router.RouteData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteDataSimilarity {
    int similarity = 0;
    private RouteData routeData;

    public RouteDataSimilarity(RouteData routeData, String[] currentRouteParts) {
        this.routeData = routeData;
        List<String> paths = Arrays.stream(routeData.getUrl().split("/")).collect(Collectors.toList());
        for (int i = 0; i < paths.size() && i < currentRouteParts.length; i++) {
            if (paths.get(i).equals(currentRouteParts[i])) {
                similarity++;
            }
        }
    }

    public RouteData getRouteData() {
        return routeData;
    }

    public int getSimilarity() {
        return similarity;
    }
}
