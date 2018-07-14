package com.github.appreciated.applayout.builder.factories;

import com.vaadin.flow.router.Route;

public class DefaultNavigationElementInfoProducer extends AbstractViewInfoProducer {

    public DefaultNavigationElementInfoProducer() {
        super(info -> info.getAnnotation(Route.class).value());
    }
}
