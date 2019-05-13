package com.github.appreciated.app.layout.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteBaseData;
import com.vaadin.flow.router.RouteData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteSimilarity {

    private Class<? extends Component> route;
    private RouteData routeData;
    private final int similarity;

    public RouteSimilarity(RouteData routeData, String[] currentRouteParts) {
        this.route = routeData.getNavigationTarget();
        this.routeData = routeData;
        this.similarity = Stream.concat(Stream.of(routeData.getUrl()),
            routeData.getRouteAliases().stream().map(RouteBaseData::getUrl))
            .mapToInt(url -> calculateSimilarity(url, currentRouteParts))
            .max()
            .orElse(0);
    }

    private int calculateSimilarity(String url, String[] currentRouteParts) {
        int calculatedSimilarity = 0;

        List<String> paths = Arrays.stream(url.split("/")).collect(Collectors.toList());
        for (int i = 0; i < paths.size() && i < currentRouteParts.length; i++) {
            if (paths.get(i).equals(currentRouteParts[i])) {
                calculatedSimilarity++;
            }
        }

        return calculatedSimilarity;
    }

    public RouteData getRouteData() {
        return routeData;
    }

    public Class<? extends Component> getRoute() {
        return route;
    }

    public int getSimilarity() {
        return similarity;
    }
}
