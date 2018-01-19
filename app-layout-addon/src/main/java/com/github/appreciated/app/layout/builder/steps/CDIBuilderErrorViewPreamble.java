package com.github.appreciated.app.layout.builder.steps;

import com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

import java.util.function.Supplier;

public class CDIBuilderErrorViewPreamble extends AbstractBuilderPreamble<CDIAppLayoutBuilder> {

    public CDIBuilderErrorViewPreamble(CDIAppLayoutBuilder builder) {
        super(builder);
    }

    /**
     * Force the user with this API to set the NavigatorProducer since without it the AppLayout won't work
     *
     * @param supplier
     * @return
     */
    public CDIBuilderViewNameProviderPreamble withErrorView(Supplier<View> supplier) {
        return new CDIBuilderViewNameProviderPreamble(getBuilder()/*.withErrorView(supplier)*/);
    }

    /**
     * Force the user with this API to set the NavigatorProducer since without it the AppLayout won't work
     *
     * @param supplier
     * @return
     */
    public CDIBuilderViewNameProviderPreamble withErrorProvider(Supplier<ViewProvider> supplier) {
        return new CDIBuilderViewNameProviderPreamble(getBuilder().withErrorProvider(supplier));
    }

}
