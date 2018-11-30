package com.github.appreciated.app.layout.component.appmenu.left.builder;

import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.ComponentBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.LeftSubmenuComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.List;


/**
 * A Builder to build {@link LeftSubmenuComponent} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class LeftSubMenuBuilder implements ComponentBuilder {

    private final String title;
    private final Icon icon;
    private List<Component> components = new ArrayList<>();

    private LeftSubMenuBuilder(String title, Icon icon) {
        this.title = title;
        this.icon = icon;
    }

    public static LeftSubMenuBuilder get(String title, Icon icon) {
        return new LeftSubMenuBuilder(title, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static LeftSubMenuBuilder get(String title) {
        return new LeftSubMenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static LeftSubMenuBuilder get(Icon icon) {
        return new LeftSubMenuBuilder(null, icon);
    }

    /**
     * Adds a MenuElement
     *
     * @param element
     * @return
     */
    public LeftSubMenuBuilder add(Component element) {
        components.add(element);
        return this;
    }

    @Override
    public Component build() {
        return new LeftSubmenuComponent(title, icon, components);
    }
}
