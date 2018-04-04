package com.github.appreciated.app.layout.builder.steps;

import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder;

public class CDIBuilderViewNameProviderPreamble extends AbstractBuilderPreamble<CDIAppLayoutBuilder> {

    public CDIBuilderViewNameProviderPreamble(CDIAppLayoutBuilder builder) {
        super(builder);
    }

    /**
     * Force the user with this API to set the NavigationElementInfoProducer since without it the AppLayout won't work
     *
     * @param provider
     * @return
     */
    public CDIAppLayoutBuilder withNavigationElementInfoProducer(AppLayoutConfiguration.NavigationElementInfoProducer provider) {
        return getBuilder().withNavigationElementInfoProducer(provider);
    }

}
