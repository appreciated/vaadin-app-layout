package com.github.appreciated.app.layout.router.navigation;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLink;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * A helper class that checks whether <a href="https://developer.android.com/training/design-navigation/ancestral-temporal">Up Navigation</a>is available for a specific route,
 * and helps in finding possible parent views
 */
public class UpNavigationHelper {

    private UpNavigationHelper() {

    }

    public static boolean routeHasUpNavigation(Class<? extends Component> navigationTarget) {
        return getClosestRoute(navigationTarget).isPresent();
    }

    public static Optional<RouteData> getClosestRoute(Class<? extends Component> navigationTarget) {
        String currentRoute = UI.getCurrent().getRouter().getUrl(navigationTarget);
        if (currentRoute.lastIndexOf("/") > 0) {
            String[] currentRouteParts = currentRoute.substring(0, (currentRoute.lastIndexOf("/"))).split("/");
            Optional<RouteSimilarity> result = UI.getCurrent().getRouter().getRoutes()
                    .stream()
                    .filter(routeData -> !routeData.getUrl().equals(currentRoute))
                    .map(routeData -> new RouteSimilarity(routeData, currentRouteParts))
                    .max(Comparator.comparingInt(RouteSimilarity::getSimilarity));
            if (result.isPresent()) {
                return Optional.ofNullable(result.get().getRouteData());
            }
        }
        return Optional.empty();
    }

    public static void performUpNavigation(Class<? extends Component> currentNavigation) {
        getClosestRoute(currentNavigation).ifPresent(routeData -> UI.getCurrent().navigate(routeData.getUrl()));
    }

    public static boolean shouldHighlight(Class<? extends Component> className, AfterNavigationEvent event) {
        String[] currentRouteParts = event.getLocation().getSegments().toArray(new String[]{});
        List<RouteData> availableRoutes = UI.getCurrent().getRouter().getRoutes();

        Optional<RouteSimilarity> result = availableRoutes.stream()
            .map(ar -> new RouteSimilarity(ar, currentRouteParts))
            .max(Comparator.comparingInt(RouteSimilarity::getSimilarity));

        return result.filter(routeSimilarity -> routeSimilarity.getRoute() == className).isPresent();
    }
}
