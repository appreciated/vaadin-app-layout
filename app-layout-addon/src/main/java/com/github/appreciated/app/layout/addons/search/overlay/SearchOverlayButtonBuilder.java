package com.github.appreciated.app.layout.addons.search.overlay;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A class to build a {@link SearchOverlayButton} with a fluent API
 *
 * @param <T>
 */
public class SearchOverlayButtonBuilder<T> {

    private Function<String, Query<T, SerializablePredicate<T>>> queryFunction;
    private Function<T, ClickNotifier> dataViewProvider;
    private ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider;
    private Consumer<T> queryResultListener;
    private Boolean closeOnQueryResult;

    public SearchOverlayButtonBuilder() {
    }

    public SearchOverlayButtonBuilder<T> withQueryProvider(Function<String, Query<T, SerializablePredicate<T>>> queryFunction) {
        this.queryFunction = queryFunction;
        return this;
    }

    public SearchOverlayButtonBuilder<T> withDataViewProvider(Function<T, ClickNotifier> dataViewProvider) {
        this.dataViewProvider = dataViewProvider;
        return this;
    }

    public SearchOverlayButtonBuilder<T> withDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider) {
        this.dataProvider = dataProvider;
        return this;
    }

    public SearchOverlayButton<T> build() {
        SearchOverlayButton<T> appBarSearchButton = new SearchOverlayButton<>();
        appBarSearchButton.setQueryProvider(queryFunction);
        appBarSearchButton.setDataViewProvider(dataViewProvider);
        appBarSearchButton.setDataProvider(dataProvider);
        appBarSearchButton.setQueryResultListener(queryResultListener);
        if (closeOnQueryResult != null) {
            appBarSearchButton.setCloseOnQueryResult(closeOnQueryResult);
        }
        return appBarSearchButton;
    }

    public SearchOverlayButtonBuilder<T> withQueryResultListener(Consumer<T> queryResultListener) {
        this.queryResultListener = queryResultListener;
        return this;
    }

    public SearchOverlayButtonBuilder<T> withCloseOnQueryResult(boolean closeOnQueryResult) {
        this.closeOnQueryResult = closeOnQueryResult;
        return this;
    }
}
