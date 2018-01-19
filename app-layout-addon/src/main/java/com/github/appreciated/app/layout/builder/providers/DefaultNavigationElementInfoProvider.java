package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.annotations.NavigatorViewName;

import java.util.Optional;

public class DefaultNavigationElementInfoProvider extends BasicViewInfoProvider {

    public DefaultNavigationElementInfoProvider() {
        super(info -> Optional.ofNullable(info.getAnnotation(NavigatorViewName.class))
                .map(menuElement -> menuElement.value())
                .orElse(null));
    }
}
