package com.github.appreciated.app.layout.component.appmenu.top.builder;

import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appmenu.left.LeftMenuComponentWrapper;
import com.github.appreciated.app.layout.component.appmenu.top.TopMenuComponent;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.List;


/**
 * A Builder to build {@link LeftMenuComponentWrapper} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class TopAppMenuBuilder {

    private List<Component> components = new ArrayList<>();

    protected TopAppMenuBuilder() {
    }

    private TopAppMenuBuilder(String title, Icon icon) {

    }

    public static TopAppMenuBuilder get() {
        return new TopAppMenuBuilder();
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static TopAppMenuBuilder get(String title) {
        return new TopAppMenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static TopAppMenuBuilder get(Icon icon) {
        return new TopAppMenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static TopAppMenuBuilder get(String title, Icon icon) {
        return new TopAppMenuBuilder(title, icon);
    }

    public TopAppMenuBuilder add(Component element) {
        return addToSection(element, Section.DEFAULT);
    }

    public TopAppMenuBuilder addToSection(Component element, Section section) {
        components.add(element);
        return this;
    }

    public NavigationElementContainer build() {
        TopMenuComponent menu = new TopMenuComponent();
        menu.add(components.toArray(new Component[0]));
        return menu;
    }
}
