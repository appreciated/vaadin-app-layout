package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.annotations.AnnotationHelper;
import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

import java.util.Optional;

public class BasicViewInfoProducer implements AppLayoutConfiguration.NavigationElementInfoProducer {

    private AnnotationValueProvider<Optional<String>> captionProvider = info -> AnnotationHelper.getAnnotationOptionally(info, MenuCaption.class).map(caption -> caption.value());
    private AnnotationValueProvider<Optional<Resource>> iconProvider = info -> AnnotationHelper.getAnnotationOptionally(info, MenuIcon.class).map(icon -> icon.value());
    private AnnotationValueProvider<Optional<String>> viewNameProvider;

    public BasicViewInfoProducer(AnnotationValueProvider<Optional<String>> viewNameProvider) {
        this.viewNameProvider = viewNameProvider;
    }

    public BasicViewInfoProducer() {
    }

    public BasicViewInfoProducer withCaptionProvider(AnnotationValueProvider<Optional<String>> captionProvider) {
        this.captionProvider = captionProvider;
        return this;
    }

    public BasicViewInfoProducer withIconProvider(AnnotationValueProvider<Optional<Resource>> iconProvider) {
        this.iconProvider = iconProvider;
        return this;
    }

    public BasicViewInfoProducer withViewNameProvider(AnnotationValueProvider<Optional<String>> viewNameProvider) {
        this.viewNameProvider = viewNameProvider;
        return this;
    }

    @Override
    public NavigationElementInfo apply(Class<? extends View> info) {
        return new NavigationElementInfo(
                captionProvider.get(info).orElse(null),
                iconProvider.get(info).orElse(null),
                viewNameProvider.get(info).orElse(null)
        );
    }


    public interface AnnotationValueProvider<T> extends Factory<T, Class<? extends View>> {
    }

}
