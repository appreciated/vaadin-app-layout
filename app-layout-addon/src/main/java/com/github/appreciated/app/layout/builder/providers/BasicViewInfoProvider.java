package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.interfaces.Provider;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

import java.io.Serializable;

public class BasicViewInfoProvider implements AppLayoutConfiguration.NavigationElementInfoProducer, Serializable {

    private AnnotationValueProvider<String> captionProvider = info -> info.getAnnotation(MenuCaption.class).value();
    private AnnotationValueProvider<Resource> iconProvider = info -> info.getAnnotation(MenuIcon.class).value();
    private AnnotationValueProvider<String> viewNameProvider;

    public BasicViewInfoProvider(AnnotationValueProvider<String> viewNameProvider) {
        this.viewNameProvider = viewNameProvider;
    }

    public BasicViewInfoProvider() {
    }

    public BasicViewInfoProvider withCaptionProvider(AnnotationValueProvider<String> captionProvider) {
        this.captionProvider = captionProvider;
        return this;
    }

    public BasicViewInfoProvider withIconProvider(AnnotationValueProvider<Resource> iconProvider) {
        this.iconProvider = iconProvider;
        return this;
    }

    public BasicViewInfoProvider withViewNameProvider(AnnotationValueProvider<String> viewNameProvider) {
        this.viewNameProvider = viewNameProvider;
        return this;
    }

    @Override
    public NavigationElementInfo apply(Class<? extends View> info) {
        return new NavigationElementInfo(
                captionProvider.get(info),
                iconProvider.get(info),
                viewNameProvider.get(info)
        );
    }

    public interface AnnotationValueProvider<T> extends Provider<T, Class<? extends View>> {
    }
}
