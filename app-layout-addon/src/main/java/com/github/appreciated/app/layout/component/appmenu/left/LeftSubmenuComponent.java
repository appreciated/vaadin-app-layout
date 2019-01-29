package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.appmenu.AppSubmenu;
import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;
import java.util.stream.Stream;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class LeftSubmenuComponent extends AppSubmenu implements NavigationElementContainer {

    private final String caption;
    private final Icon icon;
    private Factory<String, String> captionInterceptor;


    public LeftSubmenuComponent(String caption, Icon icon, List<Component> submenuElements) {
        super(caption, icon);
        getStyle().set("--app-layout-menu-padding", "0");
        getItem().getElement().getStyle().set("white-space", "nowrap");
        getToggleWrapper().getElement().appendChild(new PaperRipple().getElement());
        getToggleWrapper().getElement().getStyle().set("position", "relative");
        getMenu().getStyle().set("background", "var(--app-layout-drawer-submenu-background-color)");
        submenuElements.forEach(element1 -> getMenu().add(element1));
        this.caption = caption;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    private String getCaption() {
        if (captionInterceptor == null) {
            return caption;
        } else {
            return captionInterceptor.get(caption);
        }
    }

    @Override
    public Stream<Component> getMenuChildren() {
        return getMenu().getChildren();
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
