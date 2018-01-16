package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;

public class CDIAppLayoutBuilderWrapper {
    private final AppLayoutComponent layout;

    public CDIAppLayoutBuilderWrapper(Behaviour behaviour) {
        this.layout = behaviour.getInstance();
    }

    public CDIAppLayoutBuilderWrapper(AppLayoutComponent layout) {
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
