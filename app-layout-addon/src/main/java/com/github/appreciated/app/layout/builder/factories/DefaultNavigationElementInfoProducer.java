package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.annotations.AnnotationHelper;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;

public class DefaultNavigationElementInfoProducer extends BasicViewInfoProducer {

    public DefaultNavigationElementInfoProducer() {
        super(info -> AnnotationHelper.getAnnotationFromView(info, NavigatorViewName.class).map(a -> a.value()));
    }
}
