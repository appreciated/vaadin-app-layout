package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.SerializableConsumer;
import io.vavr.Function0;

public class NavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NavigatorAppLayoutBuilder> {
    protected NavigatorAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
    }

    public static NavigatorAppLayoutBuilder get(AppLayoutComponent layout) {
        NavigatorAppLayoutBuilder builder = new NavigatorAppLayoutBuilder(layout);
        builder.config.setNavigatorEnabled(true);
        return builder;
    }

    /**
     * Sets the Navigator which later on is being used by the AppLayout.
     *
     * @param navigator
     * @return
     */
    public NavigatorAppLayoutBuilder withNavigator(AppLayoutConfiguration.NavigatorProducer navigator) {
        this.config.setNavigatorProducer(navigator);
        return this;
    }

    /**
     * Sets the ViewProvider of the navigator instance.
     *
     * @param supplier The ViewProvider for the navigator instance
     * @return
     */
    public NavigatorAppLayoutBuilder withViewProvider(Function0<ViewProvider> supplier) {
        config.setViewProviderSupplier(supplier);
        return this;
    }

    /**
     * Sets the Error ViewProvider of the navigator instance.
     *
     * @param supplier The consumer for the navigator instance
     * @return
     */
    public NavigatorAppLayoutBuilder withErrorProvider(Function0<ViewProvider> supplier) {
        config.setErrorProvider(supplier);
        return this;
    }

    /**
     * Sets the error View of the navigator instance
     *
     * @param supplier The consumer for the navigator instance
     * @return
     */
    public NavigatorAppLayoutBuilder withErrorView(Function0<View> supplier) {
        config.setErrorView(supplier);
        return this;
    }

    /**
     * If you need to apply some changes to navigator instance which was used by the menu.
     *
     * @param consumer The consumer for the navigator instance
     * @return
     */
    public NavigatorAppLayoutBuilder withNavigatorConsumer(SerializableConsumer<Navigator> consumer) {
        config.setNavigatorConsumer(consumer);
        return this;
    }

}

