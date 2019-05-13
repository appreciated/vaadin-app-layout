package com.github.appreciated.app.layout.test.leftresponsivesmall;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.test.leftresponsivesmall.view.*;
import com.github.appreciated.app.layout.test.uis.left.AbstractLeftBehaviorBasicView;
import com.vaadin.flow.component.Component;

public class LeftResponsiveSmallBehaviourView extends AbstractLeftBehaviorBasicView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT_RESPONSIVE_SMALL;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }


}
