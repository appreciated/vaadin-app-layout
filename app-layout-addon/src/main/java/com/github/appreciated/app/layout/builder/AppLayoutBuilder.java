package com.github.appreciated.app.layout.builder;


import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AppLayoutBuilder {

    private final AppLayout instance;
    private final AppLayoutConfiguration config;

    private AppLayoutBuilder(AppLayout layout) {
        instance = layout;
        config = new AppLayoutConfiguration(instance);
    }

    public static AppLayoutBuilder get(Behaviour behaviour) {
        return new AppLayoutBuilder(behaviour.getInstance());
    }

    public static AppLayoutBuilder get(AppLayout layout) {
        return new AppLayoutBuilder(layout);
    }

    public AppLayoutBuilder withTitle(String title) {
        instance.setTitle(title);
        return this;
    }

    public AppLayoutBuilder withNavigatorProducer(AppLayoutConfiguration.NavigatorProducer navigator) {
        config.setNavigatorProducer(navigator);
        return this;
    }

    public AppLayoutBuilder withNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProvider provider) {
        config.setNavigationElementInfoProvider(provider);
        return this;
    }

    public AppLayoutBuilder withViewNameInterceptor(Provider<String, String> interceptor) {
        config.setViewNameInterceptor(interceptor);
        return this;
    }

    public AppLayoutBuilder withCaptionInterceptor(Provider<String, String> captionInterceptor) {
        config.setCaptionInterceptor(captionInterceptor);
        return this;
    }

    public AppLayoutBuilder withDesign(AppBarDesign design) {
        instance.setDesign(design);
        return this;
    }

    public AppLayoutBuilder withNavigatorConsumer(AppLayoutConfiguration.NavigatorConsumer consumer) {
        config.setNavigatorConsumer(consumer);
        return this;
    }

    /**
     * Will have not effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder withDefaultNavigationView(View element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return this;
    }

    public AppLayoutBuilder withDefaultNavigationView(Class<? extends View> element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return this;
    }

    public AppLayoutBuilder withNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        config.setNavigationElementProvider(provider);
        return this;
    }

    public AppLayoutBuilder withSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        config.setSectionProvider(provider);
        return this;
    }

    public AppLayoutBuilder withSubmenuElementProvider(ComponentProvider<Component, SubmenuNavigationElement> provider) {
        config.setSubmenuProvider(provider);
        return this;
    }

    public AppLayoutBuilder withCDI(boolean cdi) {
        config.setCDI(cdi);
        return this;
    }

    public AppLayoutBuilder add(Component element) {
        return add(element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Component element, Position position) {
        addToPosition(new CustomNavigationElement(element), position);
        return this;
    }

    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener) {
        return addClickable(caption, icon, listener, Position.DEFAULT);
    }

    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener, Position position) {
        addToPosition(new ClickableNavigationElement(caption, icon, listener), position);
        return this;
    }

    public AppLayoutBuilder add(Component... element) {
        config.getNavigationElements().addAll(Arrays.stream(element).map(component -> new CustomNavigationElement(component)).collect(Collectors.toList()));
        return this;
    }

    public AppLayoutBuilder addSection(String value) {
        config.getNavigationElements().add(new SectionNavigationElement(value));
        return this;
    }

    public AppLayoutBuilder addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element) {
        return add(caption, icon, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        return add(caption, icon, badgeHolder, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Resource icon, View element) {
        return add(caption, icon, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Class<? extends View> element) {
        return add(caption, null, element);
    }

    public AppLayoutBuilder add(String caption, View element) {
        return add(caption, null, element);
    }

    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element, Position position) {
        return add(caption, icon, null, element, position);
    }

    public AppLayoutBuilder add(String caption, String path, Resource icon, Class<? extends View> element, Position position) {
        return add(caption, path, icon, null, element, position);
    }

    public AppLayoutBuilder add(Class<? extends View> className) {
        return add(null, className, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Class<? extends View> className, Position position) {
        return add(null, className, position);
    }

    public AppLayoutBuilder add(Resource icon, Class<? extends View> className) {
        return add(icon, className, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Resource icon, Class<? extends View> className, Position position) {
        return add(new NavigatorNavigationElement(icon, className), position);
    }

    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return this;
    }

    public AppLayoutBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element), position);
        return this;
    }

    public AppLayoutBuilder add(String caption, Resource icon, View element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, element), position);
        return this;
    }

    public AppLayoutBuilder add(AbstractNavigationElement element) {
        return add(element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(AbstractNavigationElement element, Position position) {
        config.add(element, position);
        return this;
    }

    public AppLayoutBuilder addToAppBar(Component element) {
        config.getAppBarElements().add(element);
        return this;
    }

    public AppLayoutBuilder addToAppBar(Component... element) {
        config.getAppBarElements().addAll(Arrays.asList(element));
        return this;
    }

    public AppLayoutBuilder withAppBarIconComponent(Component resourceButton) {
        config.setAppBarIconComponent(resourceButton);
        return this;
    }

    private void addToPosition(AbstractNavigationElement element, Position position) {
        config.addToPosition(element, position);
    }

    public enum Position {
        HEADER, DEFAULT, FOOTER
    }

    public AppLayout build() {
        config.build();
        return instance;
    }

}
