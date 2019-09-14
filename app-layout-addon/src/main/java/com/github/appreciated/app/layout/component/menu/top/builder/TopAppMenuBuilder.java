package com.github.appreciated.app.layout.component.menu.top.builder;

import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.builder.ComponentBuilder;
import com.github.appreciated.app.layout.component.menu.left.LeftMenuComponentWrapper;
import com.github.appreciated.app.layout.component.menu.top.TopMenuComponent;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A Builder to build {@link LeftMenuComponentWrapper} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class TopAppMenuBuilder implements ComponentBuilder<TopMenuComponent> {

    private List<Component> components = new ArrayList<>();

    private TopAppMenuBuilder() {
    }

    public static TopAppMenuBuilder get() {
        return new TopAppMenuBuilder();
    }

    public TopAppMenuBuilder add(Component... element) {
        return addToSection(Section.DEFAULT, element);
    }

    public TopAppMenuBuilder addToSection(Section section, Component... element) {
        Arrays.stream(element).forEach(component -> addToSection(section, component));
        return this;
    }

    private void addToSection(Section section, Component element) {
        components.add(element);
    }

    public TopMenuComponent build() {
        TopMenuComponent menu = new TopMenuComponent();
        menu.add(components.toArray(new Component[0]));
        return menu;
    }
}
