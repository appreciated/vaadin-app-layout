package com.github.appreciated.app.layout.test.toplarge;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.test.base.AbstractTopBehaviorView;
import com.github.appreciated.app.layout.test.toplarge.view.View1;
import com.github.appreciated.app.layout.test.toplarge.view.View2;
import com.github.appreciated.app.layout.test.toplarge.view.View3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "toplarge")
public class TopLargeBehaviourView extends AbstractTopBehaviorView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.TOP_LARGE;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class};
    }

}
