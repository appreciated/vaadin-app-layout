package com.github.appreciated.app.layout.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteBaseData;
import com.vaadin.flow.router.RouteData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteSimilarity {

    private final int similarity;
    private Class<? extends Component> route;
    private RouteData routeData;
    private String[] routes;

    public RouteSimilarity(RouteData routeData, String currentRoute) {
        this.route = routeData.getNavigationTarget();
        this.routeData = routeData;
        String[] urls = Stream.concat(Stream.of(routeData.getUrl()),
                routeData.getRouteAliases().stream()
                        .map(RouteBaseData::getUrl))
                .toArray(String[]::new);
        this.similarity = getSimilarityForRoutes(urls, currentRoute);
    }

    private int getSimilarityForRoutes(String[] urls, String currentRouteParts) {
        return Arrays.stream(urls)
                .mapToInt(url -> calculateSimilarity(url, currentRouteParts))
                .max()
                .orElse(0);
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

    public RouteSimilarity(String[] urls, String currentRouteParts) {
        this.routes = urls;
        this.similarity = getSimilarityForRoutes(urls, currentRouteParts);
    }

    public String[] getRoutes() {
        return routes;
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
