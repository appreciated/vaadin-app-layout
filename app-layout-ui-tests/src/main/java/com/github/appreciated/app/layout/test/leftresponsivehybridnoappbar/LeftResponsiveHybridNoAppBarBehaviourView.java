package com.github.appreciated.app.layout.test.leftresponsivehybridnoappbar;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.test.base.AbstractLeftBehaviorBasicView;
import com.github.appreciated.app.layout.test.leftresponsivehybridnoappbar.view.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "leftresponsivehybridnoappbar")
public class LeftResponsiveHybridNoAppBarBehaviourView extends AbstractLeftBehaviorBasicView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }


}
