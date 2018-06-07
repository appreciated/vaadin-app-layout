package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

public class AbstractViewInfoProducer implements AppLayoutConfiguration.NavigationElementInfoProducer {

    private AnnotationValueProvider<String> captionProvider = info -> info.getAnnotation(MenuCaption.class) == null ? null : info.getAnnotation(MenuCaption.class).value();
    private AnnotationValueProvider<Icon> iconProvider = info -> info.getAnnotation(MenuIcon.class) == null ? null : info.getAnnotation(MenuIcon.class).value().create();
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
