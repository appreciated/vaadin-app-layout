package com.github.appreciated.app.layout.addons.profile.builder;

import com.github.appreciated.app.layout.addons.profile.ImageProfileButton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Image;

public class AppBarImageProfileButtonBuilder {

    private final ImageProfileButton appBarProfileButton;

    private AppBarImageProfileButtonBuilder(Image image) {
        appBarProfileButton = new ImageProfileButton(image);
    }

    private AppBarImageProfileButtonBuilder(String image) {
        appBarProfileButton = new ImageProfileButton(image);
    }

    public static AppBarImageProfileButtonBuilder get(Image image) {
        return new AppBarImageProfileButtonBuilder(image);
    }

    public static AppBarImageProfileButtonBuilder get(String image) {
        return new AppBarImageProfileButtonBuilder(image);
    }

    public AppBarImageProfileButtonBuilder withItem(String text) {
        appBarProfileButton.addItem(text);
        return this;
    }

    public AppBarImageProfileButtonBuilder withItem(Component component) {
        appBarProfileButton.addItem(component);
        return this;
    }

    public AppBarImageProfileButtonBuilder withItem(String text, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        appBarProfileButton.addItem(text, clickListener);
        return this;
    }

    public AppBarImageProfileButtonBuilder withItem(Component component, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        appBarProfileButton.addItem(component, clickListener);
        return this;
    }

    public ImageProfileButton build() {
        return appBarProfileButton;
    }
}