package com.github.appreciated.app.layout.test.annotation;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.test.annotation.view.View1;
import com.github.appreciated.app.layout.test.annotation.view.View2;
import com.github.appreciated.app.layout.test.annotation.view.View3;
import com.github.appreciated.app.layout.test.annotation.view.View4;
import com.github.appreciated.app.layout.test.annotation.view.View5;
import com.github.appreciated.app.layout.test.annotation.view.View6;
import com.github.appreciated.app.layout.test.annotation.view.View7;
import com.github.appreciated.app.layout.test.annotation.view.View8;
import com.github.appreciated.app.layout.test.annotation.view.View9;
import com.github.appreciated.app.layout.test.uis.left.AbstractLeftBehaviorAnnotationView;
import com.vaadin.flow.component.Component;

public class NavigationView extends AbstractLeftBehaviorAnnotationView {

    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT_RESPONSIVE;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }
}
