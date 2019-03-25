package com.github.appreciated.app.layout.router.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteData;

import java.util.Comparator;
import java.util.Optional;

/**
 * A helper class that checks whether <a href="https://developer.android.com/training/design-navigation/ancestral-temporal">Up Navigation</a>is available for a specific route,
 * and helps in finding possible parent views
 */
public class UpNavigationHelper {
    public static boolean routeHasUpNavigation(Class<? extends Component> navigationTarget) {
        return getClosestRoute(navigationTarget).isPresent();
    }

    public static Optional<RouteData> getClosestRoute(Class<? extends Component> navigationTarget) {
        String currentRoute = UI.getCurrent().getRouter().getUrl(navigationTarget);
        if (currentRoute.lastIndexOf("/") > 0) {
            String[] currentRouteParts = currentRoute.substring(0, (currentRoute.lastIndexOf("/"))).split("/");
            Optional<RouteDataSimilarity> result = UI.getCurrent().getRouter().getRoutes()
                    .stream()
                    .filter(routeData -> !routeData.getUrl().equals(currentRoute))
                    .map(routeData -> new RouteDataSimilarity(routeData, currentRouteParts))
                    .max(Comparator.comparingInt(RouteDataSimilarity::getSimilarity));
            if (result.isPresent()) {
                return Optional.ofNullable(result.get().getRouteData());
            }
        }
        return Optional.empty();
    }

    public static void performUpNavigation(Class<? extends Component> currentNavigation) {
        Optional<RouteData> closest = getClosestRoute(currentNavigation);
        closest.ifPresent(routeData -> UI.getCurrent().navigate(routeData.getUrl()));
    }
}
