package com.github.appreciated.app.layout.builder.steps;

import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder;

public class CDIBuilderNavigatorPreamble extends AbstractBuilderPreamble<CDIAppLayoutBuilder> {

    public CDIBuilderNavigatorPreamble(CDIAppLayoutBuilder builder) {
        super(builder);
    }

    /**
     * Force the user with this API to set the NavigatorProducer since without it the AppLayout won't work
     *
     * @param producer
     * @return
     */
    public CDIBuilderViewNameProviderPreamble withNavigatorProducer(AppLayoutConfiguration.NavigatorProducer producer) {
        return new CDIBuilderViewNameProviderPreamble(getBuilder().withNavigatorProducer(producer));
    }

}
