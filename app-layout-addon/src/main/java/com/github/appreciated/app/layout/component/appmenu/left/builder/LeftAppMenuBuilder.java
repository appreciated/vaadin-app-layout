package com.github.appreciated.app.layout.component.appmenu.left.builder;

import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appmenu.left.LeftMenuComponent;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;


/**
 * A Builder to build {@link LeftMenuComponent} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class LeftAppMenuBuilder {

    ArrayList<Component> components = new ArrayList<>();

    protected LeftAppMenuBuilder() {
    }

    private LeftAppMenuBuilder(String title, Icon icon) {

    }

    public static LeftAppMenuBuilder get() {
        return new LeftAppMenuBuilder();
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static LeftAppMenuBuilder get(String title) {
        return new LeftAppMenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static LeftAppMenuBuilder get(Icon icon) {
        return new LeftAppMenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static LeftAppMenuBuilder get(String title, Icon icon) {
        return new LeftAppMenuBuilder(title, icon);
    }


    public LeftAppMenuBuilder add(Component element) {
        return addToSection(element, Section.DEFAULT);
    }

    public LeftAppMenuBuilder addToSection(Component element, Section section) {
        components.add(element);
        return this;
    }

    public NavigationElementContainer build() {
        LeftMenuComponent menu = new LeftMenuComponent();
        menu.add(components.toArray(new Component[0]));
        return menu;
    }
}
