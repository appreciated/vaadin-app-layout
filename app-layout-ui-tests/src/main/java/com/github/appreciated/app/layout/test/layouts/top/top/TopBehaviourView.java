package com.github.appreciated.app.layout.test.layouts.top.top;

import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.applayout.TopLayouts;
import com.github.appreciated.app.layout.test.base.AbstractTopBehaviorView;
import com.github.appreciated.app.layout.test.layouts.top.top.view.View1;
import com.github.appreciated.app.layout.test.layouts.top.top.view.View2;
import com.github.appreciated.app.layout.test.layouts.top.top.view.View3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "top")
public class TopBehaviourView extends AbstractTopBehaviorView {
    @Override
    public Class<? extends AppLayout> getVariant() {
        return TopLayouts.Top.class;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class};
    }

}
