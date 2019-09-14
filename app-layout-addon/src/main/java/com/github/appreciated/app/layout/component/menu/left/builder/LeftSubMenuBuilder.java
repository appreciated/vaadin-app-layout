package com.github.appreciated.app.layout.component.menu.left.builder;

import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.builder.ComponentBuilder;
import com.github.appreciated.app.layout.component.menu.left.LeftSubmenu;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A Builder to build {@link LeftSubmenu} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class LeftSubMenuBuilder implements ComponentBuilder<LeftSubmenu> {

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
     * @param elements
     * @return
     */
    public LeftSubMenuBuilder add(Component... elements) {
        components.addAll(Arrays.asList(elements));
        return this;
    }

    @Override
    public LeftSubmenu build() {
        return new LeftSubmenu(title, icon, components);
    }
}
