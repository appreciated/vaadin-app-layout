package com.github.appreciated.app.layout.builder.impl;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;

public class CDIAppLayoutBuilderWrapper {
    private final AppLayout layout;

    public CDIAppLayoutBuilderWrapper(Behaviour behaviour) {
        this.layout = behaviour.getInstance();
    }

    public CDIAppLayoutBuilderWrapper(AppLayout layout) {
        this.layout = layout;
    }

    /**
     * Force the user with this API to set the navigator provider to
     *
     * @param producer
     * @return
     */
    public CDIAppLayoutBuilder withNavigatorProvider(AppLayoutConfiguration.NavigatorProducer producer) {
        return CDIAppLayoutBuilder.get(layout).withNavigatorProducer(producer);
    }

}
