package com.github.appreciated.app.layout.session;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.button.NavigationBadgeButton;
import com.vaadin.ui.UI;
import io.vavr.control.Option;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT_ACTIVE;

public class AppLayoutSessionHelper {
    public static String UI_SESSION_KEY = "app-layout-menu-button-active";
    public static String UI_SESSION_KEY_VARIANT = "app-layout-variant-active";

    public static void updateActiveElementSessionData(NavigatorNavigationElement element) {
        setActiveNavigationElement(element);
    }

    public static void removeStyleFromCurrentlyActiveNavigationElement() {
        getActiveNavigationElement().peek(element1 -> UI.getCurrent().access(() -> element1.getComponent().removeStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE)));
    }

    public static Option<NavigatorNavigationElement> getActiveNavigationElement() {
        Object object = UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY);
        if (object instanceof NavigatorNavigationElement) {
            return Option.of((NavigatorNavigationElement) object);
        } else {
            return Option.none();
        }
    }

    public static void setActiveNavigationElement(NavigatorNavigationElement element) {
        removeStyleFromCurrentlyActiveNavigationElement();
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        UI ui = UI.getCurrent();
        ui.access(() -> button.addStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE));
        ui.getSession().setAttribute(UI_SESSION_KEY, element);
    }

    public static Behaviour getActiveVariant() {
        return (Behaviour) UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY_VARIANT);
    }

    public static void setActiveVariant(Behaviour variant) {
        UI.getCurrent().getSession().setAttribute(UI_SESSION_KEY_VARIANT, variant);
    }
}
