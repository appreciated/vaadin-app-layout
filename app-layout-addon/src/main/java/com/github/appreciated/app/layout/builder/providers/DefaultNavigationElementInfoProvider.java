package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.annotations.NavigatorViewName;

public class DefaultNavigationElementInfoProvider extends BasicViewInfoProvider {

    public DefaultNavigationElementInfoProvider() {
        super(info -> info.getAnnotation(NavigatorViewName.class).value());
    }
}
