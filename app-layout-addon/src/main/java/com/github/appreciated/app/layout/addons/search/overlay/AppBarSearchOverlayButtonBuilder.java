package com.github.appreciated.app.layout.addons.search.overlay;

import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.function.Function;

/**
 * A class to build a {@link AppBarSearchOverlayButton} with a fluent API
 *
 * @param <T>
 */
public class AppBarSearchOverlayButtonBuilder<T> {

    private Function<String, Query<T, SerializablePredicate<T>>> queryFunction;
    private DataViewProvider<T> dataViewProvider;
    private ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider;

    public AppBarSearchOverlayButtonBuilder() {
    }

    public AppBarSearchOverlayButtonBuilder<T> withQueryProvider(Function<String, Query<T, SerializablePredicate<T>>> queryFunction) {
        this.queryFunction = queryFunction;
        return this;
    }

    public AppBarSearchOverlayButtonBuilder<T> withDataViewProvider(DataViewProvider<T> dataViewProvider) {
        this.dataViewProvider = dataViewProvider;
        return this;
    }

    public AppBarSearchOverlayButtonBuilder<T> withDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider) {
        this.dataProvider = dataProvider;
        return this;
    }

    public AppBarSearchOverlayButton<T> build() {
        AppBarSearchOverlayButton<T> appBarSearchButton = new AppBarSearchOverlayButton<>();
        appBarSearchButton.setQueryProvider(queryFunction);
        appBarSearchButton.setDataViewProvider(dataViewProvider);
        appBarSearchButton.setDataProvider(dataProvider);
        return appBarSearchButton;
    }

}
