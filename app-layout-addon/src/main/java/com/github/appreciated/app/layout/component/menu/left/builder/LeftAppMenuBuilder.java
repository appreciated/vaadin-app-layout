package com.github.appreciated.app.layout.component.menu.left.builder;

import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.LeftMenuComponentWrapper;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A Builder to build {@link LeftMenuComponentWrapper} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class LeftAppMenuBuilder {

    private final List<Component> components = new ArrayList<>();
    private final List<Component> header = new ArrayList<>();
    private final List<Component> body = new ArrayList<>();
    private final List<Component> footer = new ArrayList<>();
    private boolean sticky;

    private LeftAppMenuBuilder() {
    }

    public static LeftAppMenuBuilder get() {
        return new LeftAppMenuBuilder();
    }

    public LeftAppMenuBuilder add(Component... element) {
        return addToSection(Section.DEFAULT, element);
    }

    public LeftAppMenuBuilder add(String caption, VaadinIcon icon, Class<? extends Component> className) {
        return add(new LeftNavigationItem(caption, icon, className));
    }

    public LeftAppMenuBuilder add(String caption, Component icon, Class<? extends Component> className) {
        return add(new LeftNavigationItem(caption, icon, className));
    }

    public LeftAppMenuBuilder addToSection(Section section, Component... element) {
        Arrays.stream(element).forEach(component -> addToSection(component, section));
        return this;
    }

    private LeftAppMenuBuilder addToSection(Component element, Section section) {
        switch (section) {
            case HEADER:
                header.add(element);
                break;
            case FOOTER:
                footer.add(element);
                break;
            default:
                body.add(element);
        }
        return this;
    }

    public LeftAppMenuBuilder withStickyFooter() {
        this.sticky = true;
        return this;
    }

    public Component build() {
        components.addAll(header);
        LeftMenuComponentWrapper menu = new LeftMenuComponentWrapper();
        components.addAll(body);
        if (sticky) {
            menu.getMenu().getStyle().set("display", "flex");
            Div div = new Div();
            div.setWidth("100%");
            div.setHeight("0px");
            div.getStyle().set("flex", "1 1");
            components.add(div);
        }
        components.addAll(footer);
        menu.add(components.toArray(new Component[0]));
        return menu;
    }
}
