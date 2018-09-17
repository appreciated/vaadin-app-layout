package com.github.appreciated.applayout.builder.factories;

import com.github.appreciated.applayout.annotations.Caption;
import com.github.appreciated.applayout.builder.AppLayoutConfiguration;
import com.github.appreciated.applayout.builder.interfaces.Factory;
import com.github.appreciated.applayout.entity.NavigationElementInfo;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

public class AbstractViewInfoProducer implements AppLayoutConfiguration.NavigationElementInfoProducer {

    private AnnotationValueProvider<String> captionProvider = info -> info.getAnnotation(Caption.class) == null ? null : info.getAnnotation(Caption.class).value();
    private AnnotationValueProvider<Icon> iconProvider = info -> info.getAnnotation(com.github.appreciated.applayout.annotations.Icon.class) == null ? null : info.getAnnotation(com.github.appreciated.applayout.annotations.Icon.class).value().create();
    private AnnotationValueProvider<String> routeProvider;

    public AbstractViewInfoProducer(AnnotationValueProvider<String> routeProvider) {
        this.routeProvider = routeProvider;
    }

    public AbstractViewInfoProducer() {
    }

    public AbstractViewInfoProducer withCaptionProvider(AnnotationValueProvider<String> captionProvider) {
        this.captionProvider = captionProvider;
        return this;
    }

    public AbstractViewInfoProducer withIconProvider(AnnotationValueProvider<Icon> iconProvider) {
        this.iconProvider = iconProvider;
        return this;
    }

    public AbstractViewInfoProducer withViewNameProvider(AnnotationValueProvider<String> routeProvider) {
        this.routeProvider = routeProvider;
        return this;
    }

    @Override
    public NavigationElementInfo apply(Class<? extends HasElement> info) {
        return new NavigationElementInfo(
                captionProvider.get(info),
                iconProvider.get(info),
                routeProvider.get(info)
        );
    }

    public interface AnnotationValueProvider<T> extends Factory<T, Class<? extends HasElement>> {
    }
}
