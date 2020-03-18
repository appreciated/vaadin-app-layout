package com.github.appreciated.app.layout.navigation;

import com.github.appreciated.app.layout.session.UIAttributes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
 * A helper class that checks whether <a href="https://developer.android.com/training/design-navigation/ancestral-temporal">Up Navigation</a>is available for a specific route,
 * and helps in finding possible parent views
 */
public class UpNavigationHelper implements Serializable {
    private static final long serialVersionUID = 1L;

    private HashMap<RouteData, String> registeredRoutes = new HashMap<>();

    private UpNavigationHelper() {

    }

    public static boolean routeHasUpNavigation(Class<? extends Component> navigationTarget) {
        return getRoutesForClassName(navigationTarget)
                .anyMatch(data -> !getUpNavigationHelper().registeredRoutes.containsKey(data));
    }

    private static Stream<RouteData> getRoutesForClassName(Class<? extends Component> className) {
        return RouteConfiguration.forSessionScope()
                .getAvailableRoutes()
                .stream()
                .filter(routeData -> routeData.getNavigationTarget() == className);
    }

    public static UpNavigationHelper getUpNavigationHelper() {
        if (UIAttributes.get(UpNavigationHelper.class) == null) {
            setUpNavigationHelper();
        }
        return UIAttributes.get(UpNavigationHelper.class);
    }

    public static void setUpNavigationHelper() {
        UIAttributes.set(UpNavigationHelper.class, new UpNavigationHelper());
    }

    public static Optional<RouteSimilarity> getClosestRoute(String url, String[] availableRoutes) {
        if (url.lastIndexOf("/") > 0) {
            Optional<RouteSimilarity> result = Arrays.stream(availableRoutes)
                    .filter(routeData -> !routeData.equals(url))
                    .map(routeData -> new RouteSimilarity(routeData, url))
                    .max(Comparator.comparingInt(RouteSimilarity::getSimilarity));
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    public static void performUpNavigation(Class<? extends Component> currentNavigation) {
        getClosestRoute(RouteConfiguration.forSessionScope().getUrl(currentNavigation))
                .ifPresent(routeData -> UI.getCurrent().navigate(routeData.getUrl()));
    }

    public static Optional<RouteData> getClosestRoute(String url) {
        if (url.lastIndexOf("/") > 0) {
            Optional<RouteSimilarity> result = RouteConfiguration.forApplicationScope()
                    .getAvailableRoutes()
                    .stream()
                    .filter(routeData -> !routeData.getUrl().equals(url) ||
                            routeData.getRouteAliases().stream().anyMatch(routeAliasData -> routeAliasData.getUrl().equals(url)))
                    .map(routeData -> new RouteSimilarity(routeData, url))
                    .filter(routeSimilarity -> routeSimilarity.getSimilarity() > 0)
                    .max(Comparator.comparingInt(RouteSimilarity::getSimilarity));
            if (result.isPresent()) {
                return Optional.ofNullable(result.get().getRouteData());
            }
        }
        return Optional.empty();
    }

    public static boolean shouldHighlight(Class<? extends Component> className, Location location) {
        String currentRoute = location.getSegments().stream().reduce((s1, s2) -> s1 + "/" + s2).get();

        Set<RouteData> routes = getUpNavigationHelper().registeredRoutes.keySet();
        Optional<RouteSimilarity> result = routes.stream()
                .map(s -> new RouteSimilarity(s, currentRoute))
                .max(Comparator.comparingInt(RouteSimilarity::getSimilarity));

        return result.filter(routeSimilarity -> routeSimilarity.getRoute() == className).isPresent();
    }

    /**
     * We need to be able to differentiate between routes that have been added to the menu and generally available routes.
     *
     * @param className
     */
    public static void registerNavigationRoute(Class<? extends Component> className) {
        getUpNavigationHelper().register(className);
    }

    public void register(Class<? extends Component> className) {
        getRoutesForClassName(className)
                .forEach(routeData -> {
                    registeredRoutes.put(routeData, RouteConfiguration.forSessionScope().getUrl(className));
                });
    }

}
