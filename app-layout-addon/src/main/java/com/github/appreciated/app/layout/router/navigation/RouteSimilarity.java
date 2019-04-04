package com.github.appreciated.app.layout.router.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteSimilarity {
    private Class<? extends Component> route;
    private RouteData routeData;
    int similarity = 0;

    public RouteSimilarity(RouteData routeData, String[] currentRouteParts) {
        this.routeData = routeData;
        List<String> paths = Arrays.stream(routeData.getUrl().split("/")).collect(Collectors.toList());
        for (int i = 0; i < paths.size() && i < currentRouteParts.length; i++) {
            if (paths.get(i).equals(currentRouteParts[i])) {
                similarity++;
            }
        }
    }

    public RouteSimilarity(Class<? extends Component> route, String[] currentRouteParts) {
        this.route = route;
        List<String> paths = Arrays.stream(UI.getCurrent().getRouter().getUrl(route).split("/")).collect(Collectors.toList());
        for (int i = 0; i < paths.size() && i < currentRouteParts.length; i++) {
            if (paths.get(i).equals(currentRouteParts[i])) {
                similarity++;
            }
        }
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
