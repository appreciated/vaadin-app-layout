package com.github.appreciated.applayout.builder.interfaces;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.router.Route;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This interface is for Components that contain Elements that are clickable and the interaction with it leads to route
 * changes.
 * This is especially important to highlight navigation elements if the view they are related with was navigated to,
 * without the elements knowledge (f.e. loading the page with a nested url).
 */
public interface NavigationElementContainer extends NavigationElement {

    default boolean setActiveNavigationComponent(Class<? extends HasElement> element) {
        return setActiveNavigationComponent(getChildren(), element);
    }

    default boolean setActiveNavigationComponent(Stream<Component> elements, Class<? extends HasElement> content) {
        return elements
                .filter(component -> component instanceof NavigationElement)
                .map(component -> ((NavigationElement) component).setActiveNavigationComponent(content))
                .reduce((first, next) -> first || next).orElse(false);
    }

    default Class<? extends HasElement> getClosestNavigationElement(Class<? extends HasElement> element) {
        return getClosestNavigationElement(getChildren(), element);
    }

    default Class<? extends HasElement> getClosestNavigationElement(Stream<Component> elements, Class<? extends HasElement> content) {
        return elements
                .filter(component -> component instanceof NavigationElement)
                .map(component -> {
                    if (component instanceof NavigationElementComponent) {
                        Class<? extends HasElement> result = ((NavigationElementComponent) component).getNavigationElement();
                        return result;
                    } else {
                        Class<? extends HasElement> result = ((NavigationElementContainer) component).getClosestNavigationElement(content);
                        return result;
                    }
                })
                .reduce((first, next) -> compareRoute(content, first, next)).orElse(null);
    }

    default Class<? extends HasElement> compareRoute(Class<? extends HasElement> compare, Class<? extends HasElement> route1, Class<? extends HasElement> route2) {
        if (route2 == null) {
            return route1;
        }
        if (route1 == null) {
            return route2;
        }
        int score1 = computeClosenessScore(compare, route1);
        int score2 = computeClosenessScore(compare, route2);
        return score1 > score2 ? route1 : route2;
    }

    default int computeClosenessScore(Class<? extends HasElement> compare, Class<? extends HasElement> compare2) {
        String[] desiredRoute = getRoute(compare).get().value().split("/");
        String[] elementRoute = getRoute(compare2).map(route -> route.value().split("/")).orElse(null);
        int score = 0;
        for (; score < elementRoute.length; score++) {
            if (desiredRoute.length >= score && !elementRoute[score].equals(desiredRoute[score])) {
                return score;
            }
        }
        return score;
    }

    default Optional<Route> getRoute(Class<? extends HasElement> element) {
        if (element.getAnnotation(Route.class) != null)
            return Optional.of(element.getAnnotation(Route.class));
        return Optional.empty();
    }

    Stream<Component> getChildren();

    Component getComponent();
}
