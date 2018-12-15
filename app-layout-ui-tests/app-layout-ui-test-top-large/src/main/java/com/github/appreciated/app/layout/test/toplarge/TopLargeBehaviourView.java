package com.github.appreciated.app.layout.test.toplarge;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.test.toplarge.view.View1;
import com.github.appreciated.app.layout.test.toplarge.view.View2;
import com.github.appreciated.app.layout.test.toplarge.view.View3;
import com.github.appreciated.app.layout.test.uis.left.AbstractTopBehaviorView;
import com.vaadin.flow.component.Component;

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
