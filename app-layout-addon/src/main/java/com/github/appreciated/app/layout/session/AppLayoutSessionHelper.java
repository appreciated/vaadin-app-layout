package com.github.appreciated.app.layout.session;

import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.vaadin.ui.UI;

import java.util.Optional;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT_ACTIVE;

public class AppLayoutSessionHelper {
    public static String UI_SESSION_KEY = "app-layout-menu-button-active";
    public static String UI_SESSION_KEY_VARIANT = "app-layout-variant-active";

    public static void updateActiveElementSessionData(NavigatorNavigationElement element) {
        removeStyleFromCurrentlyActiveNavigationElement();
        setActiveNavigationElement(element);
    }

    public static void removeStyleFromCurrentlyActiveNavigationElement() {
        getActiveNavigationElement().ifPresent(element1 -> element1.getComponent().removeStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE));
    }

    public static Optional<NavigatorNavigationElement> getActiveNavigationElement() {
        Object object = UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY);
        if (object instanceof NavigatorNavigationElement) {
            return Optional.of((NavigatorNavigationElement) object);
        } else {
            return Optional.empty();
        }
    }

    public static void setActiveNavigationElement(NavigatorNavigationElement element) {
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        button.addStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE);
        UI.getCurrent().getSession().setAttribute(UI_SESSION_KEY, element);
    }

    public static DrawerVariant getActiveVariant() {
        return (DrawerVariant) UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY_VARIANT);
    }

    public static void setActiveVariant(DrawerVariant variant) {
        UI.getCurrent().getSession().setAttribute(UI_SESSION_KEY_VARIANT, variant);
    }
}
