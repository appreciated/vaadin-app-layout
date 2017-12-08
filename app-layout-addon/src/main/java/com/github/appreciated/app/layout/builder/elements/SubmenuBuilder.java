package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

public class SubmenuBuilder {

    private final String title;
    private final Resource resource;
    private List<AbstractNavigationElement> submenuElements = new ArrayList<>();
    private AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider;

    private SubmenuBuilder(String title, Resource resource) {
        this.title = title;
        this.resource = resource;
    }

    public static SubmenuBuilder get(String title) {
        return new SubmenuBuilder(title, null);
    }

    public static SubmenuBuilder get(Resource icon) {
        return new SubmenuBuilder(null, icon);
    }

    public static SubmenuBuilder get(String title, Resource icon) {
        return new SubmenuBuilder(title, icon);
    }

    public SubmenuBuilder add(String caption) {
        return this.add(caption, (Resource) null);
    }

    public SubmenuBuilder add(String caption, Resource icon) {
        return add(caption, icon, null, (View) null);
    }

    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder) {
        return add(caption, icon, badgeHolder, (View) null);
    }

    public SubmenuBuilder add(String caption, View element) {
        return add(caption, null, (DefaultBadgeHolder) null, element);
    }

    public SubmenuBuilder add(String caption, Resource icon, View element) {
        return add(caption, icon, null, element);
    }

    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        this.add(new NavigatorNavigationElement(caption, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder add(Class<? extends View> className) {
        return add((Resource) null, className);
    }

    public SubmenuBuilder add(Resource icon, Class<? extends View> className) {
        return add(new NavigatorNavigationElement(icon, className));
    }

    public SubmenuBuilder add(String caption, Class<? extends View> element) {
        return this.add(caption, null, element);
    }

    public SubmenuBuilder add(String caption, Resource icon, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, element));
        return this;
    }

    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, Class<? extends View> element) {
        return this.add(caption, path, icon, null, element);
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, View element) {
        return this.add(caption, path, icon, null, element);
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        this.add(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder addClickable(Resource icon, Button.ClickListener listener) {
        return addClickable(null, icon, listener);
    }

    public SubmenuBuilder addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    public SubmenuBuilder addClickable(String caption, Resource icon, Button.ClickListener listener) {
        this.submenuElements.add(new ClickableNavigationElement(caption, icon, listener));
        return this;
    }

    public SubmenuBuilder add(Component element) {
        this.submenuElements.add(new ComponentNavigationElement(element));
        return this;
    }

    public SubmenuBuilder add(AbstractNavigationElement element) {
        this.submenuElements.add(element);
        return this;
    }

    public void setNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider) {
        this.navigationElementInfoProvider = navigationElementInfoProvider;
    }

    public List<AbstractNavigationElement> getSubmenuElements() {
        return submenuElements;
    }

    public SubmenuNavigationElement build() {
        return new SubmenuNavigationElement(title, resource, submenuElements);
    }
}
