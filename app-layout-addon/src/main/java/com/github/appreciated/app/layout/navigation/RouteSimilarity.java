package com.github.appreciated.app.layout.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteSimilarity {

    private final int similarity;
    private Class<? extends Component> route;
    private RouteData routeData;
    private String routeString;

    public RouteSimilarity(RouteData routeData, String currentRoute) {
        this.route = routeData.getNavigationTarget();
        this.routeData = routeData;
        this.similarity = calculateSimilarity(routeData.getUrl(), currentRoute);
    }

    private int calculateSimilarity(String url, String currentRoute) {
        int calculatedSimilarity = 0;
        List<String> paths = Arrays.stream(url.split("/")).collect(Collectors.toList());
        List<String> currentRoutepaths = Arrays.stream(currentRoute.split("/")).collect(Collectors.toList());
        for (int i = 0; i < paths.size() && i < currentRoutepaths.size(); i++) {
            if (paths.get(i).equals(currentRoutepaths.get(i))) {
                calculatedSimilarity++;
            }
        }
        if (calculatedSimilarity == currentRoutepaths.size() && paths.size() == currentRoutepaths.size()) {
            calculatedSimilarity++;
        }
        return calculatedSimilarity;
    }

    public RouteSimilarity(String url, String currentRouteParts) {
        routeString = url;
        this.similarity = calculateSimilarity(url, currentRouteParts);
    }


    public RouteData getRouteData() {
        return routeData;
    }

    public Class<? extends Component> getRoute() {
        return route;
    }

    public String getRouteString() {
        return routeString;
    }


    public int getSimilarity() {
        return similarity;
    }
}
