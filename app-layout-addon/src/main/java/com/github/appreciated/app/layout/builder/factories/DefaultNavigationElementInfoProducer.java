package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.annotations.NavigatorViewName;

public class DefaultNavigationElementInfoProducer extends BasicViewInfoProducer {

    public DefaultNavigationElementInfoProducer() {
        super(info -> info.getAnnotation(NavigatorViewName.class).value());
    }
}
