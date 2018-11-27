package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.appmenu.AppSubmenu;
import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;
import java.util.Optional;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class LeftSubmenuComponent extends AppSubmenu implements NavigationElementContainer {

    private final String title;
    private final Icon icon;
    private Factory<String, String> captionInterceptor;

    public LeftSubmenuComponent(String title, Icon icon, List<Component> submenuElements) {
        super(title, icon);
        getItem().getElement().getStyle().set("white-space", "nowrap");
        getToggleWrapper().getElement().appendChild(new PaperRipple().getElement());
        getToggleWrapper().getElement().getStyle().set("position", "relative");
        getMenu().getStyle().set("background", "var(--app-layout-drawer-submenu-background-color)");
        submenuElements.forEach(element1 -> getMenu().add(element1));
        this.title = title;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getTitle() {
        if (captionInterceptor == null) {
            return title;
        } else {
            return captionInterceptor.get(title);
        }
    }

    @Override
    public boolean setActiveNavigationComponent(Class<? extends HasElement> element) {
        return setActiveNavigationComponent(getMenu().getChildren(), element);
    }

    @Override
    public Optional<Class<? extends HasElement>> getClosestNavigationElement(Class<? extends HasElement> element) {
        return Optional.of(getClosestNavigationElement(getMenu().getChildren(), element));
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
