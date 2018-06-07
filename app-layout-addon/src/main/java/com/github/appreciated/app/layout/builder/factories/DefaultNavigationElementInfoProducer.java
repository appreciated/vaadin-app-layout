package com.github.appreciated.app.layout.builder.factories;

import com.vaadin.flow.router.Route;

public class DefaultNavigationElementInfoProducer extends AbstractViewInfoProducer {

    public DefaultNavigationElementInfoProducer() {
        super(info -> info.getAnnotation(Route.class).value());
    }
}
