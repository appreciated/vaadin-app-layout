package com.github.appreciated.app.layout.test.annotation;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.test.annotation.view.*;
import com.github.appreciated.app.layout.test.base.AbstractLeftBehaviorAnnotationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "annotation")
public class AnnotationView extends AbstractLeftBehaviorAnnotationView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT_RESPONSIVE;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }


}
