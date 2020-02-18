package com.github.appreciated.app.layout.addons.search.overlay;

import java.util.function.Consumer;
import java.util.function.Function;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

/**
 * A class to build a {@link SearchOverlayButton} with a fluent API
 *
 * @param <T>
 */
public class SearchOverlayButtonBuilder<T, F> {

    private Function<String, Query<T, F>> queryFunction;
    private Function<T, ClickNotifier> dataViewProvider;
    private DataProvider<T, F> dataProvider;
    private Consumer<T> queryResultListener;
    private Boolean closeOnQueryResult;

    public SearchOverlayButtonBuilder() {
    }

    public SearchOverlayButtonBuilder<T, F> withQueryProvider(Function<String, Query<T, F>> queryFunction) {
        this.queryFunction = queryFunction;
        return this;
    }

    public SearchOverlayButtonBuilder<T, F> withDataViewProvider(Function<T, ClickNotifier> dataViewProvider) {
        this.dataViewProvider = dataViewProvider;
        return this;
    }

    public SearchOverlayButtonBuilder<T, F> withDataProvider(DataProvider<T, F> dataProvider) {
        this.dataProvider = dataProvider;
        return this;
    }

    public SearchOverlayButton<T, F> build() {
        SearchOverlayButton<T, F> appBarSearchButton = new SearchOverlayButton<>();
        appBarSearchButton.setQueryProvider(queryFunction);
        appBarSearchButton.setDataViewProvider(dataViewProvider);
        appBarSearchButton.setDataProvider(dataProvider);
        appBarSearchButton.setQueryResultListener(queryResultListener);
        if (closeOnQueryResult != null) {
            appBarSearchButton.setCloseOnQueryResult(closeOnQueryResult);
        }
        return appBarSearchButton;
    }

    public SearchOverlayButtonBuilder<T, F> withQueryResultListener(Consumer<T> queryResultListener) {
        this.queryResultListener = queryResultListener;
        return this;
    }

    public SearchOverlayButtonBuilder<T, F> withCloseOnQueryResult(boolean closeOnQueryResult) {
        this.closeOnQueryResult = closeOnQueryResult;
        return this;
    }
}
