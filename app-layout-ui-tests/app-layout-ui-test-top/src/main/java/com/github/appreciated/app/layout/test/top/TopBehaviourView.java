package com.github.appreciated.app.layout.test.top;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.test.top.view.View1;
import com.github.appreciated.app.layout.test.top.view.View2;
import com.github.appreciated.app.layout.test.top.view.View3;
import com.github.appreciated.app.layout.test.uis.left.AbstractTopBehaviorView;
import com.vaadin.flow.component.Component;

public class TopBehaviourView extends AbstractTopBehaviorView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.TOP;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class};
    }


}
