package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.behaviour.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.vaadin.navigator.View;

import java.util.Optional;

public class DefaultViewInfoProvider implements AppLayoutConfiguration.NavigationElementInfoProvider {

    private AnnotationValueProvider provider;

    DefaultViewInfoProvider(AnnotationValueProvider provider) {
        this.provider = provider;
    }

    @Override
    public NavigationElementInfo apply(Class<? extends View> aClass) {
        return new NavigationElementInfo(
                Optional.ofNullable(aClass.getAnnotation(MenuCaption.class))
                        .map(menuElement -> menuElement.value())
                        .orElse(provider.get(aClass)),
                Optional.ofNullable(aClass.getAnnotation(MenuIcon.class))
                        .map(menuElement -> menuElement.value())
                        .orElse(null),
                provider.get(aClass));
    }

    public interface AnnotationValueProvider extends Provider<String, Class<? extends View>> {
    }
}
