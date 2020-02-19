package com.github.appreciated.app.layout.addons.search.overlay;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

public class SearchOverlayButton<T, F> extends IconButton {
    private final SearchOverlayView<T, F> searchView;

    public SearchOverlayButton() {
        this(VaadinIcon.SEARCH);
    }

    public SearchOverlayButton(VaadinIcon icon) {
        this(icon.create());
    }

    public SearchOverlayButton(Component icon) {
        super(icon);
        searchView = new SearchOverlayView<>();
        addClickListener(event -> searchView.open());
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        searchView.getElement().removeFromParent();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        attachEvent.getUI().add(searchView);
    }

    public SearchOverlayView<T, F> getSearchView() {
        return searchView;
    }

    public void setDataViewProvider(Function<T, ClickNotifier> provider) {
        this.searchView.setDataViewProvider(provider);
    }

    public void setDataProvider(DataProvider<T, F> dataProvider) {
        this.searchView.setDataProvider(dataProvider);
    }

    public void setQueryProvider(Function<String, Query<T, F>> query) {
        this.searchView.setQueryProvider(query);
    }

    public void setQueryResultListener(Consumer<T> queryResultListener) {
        this.searchView.setQueryResultListener(queryResultListener);
    }

    public void setCloseOnQueryResult(boolean closeOnQueryResult) {
        this.searchView.setCloseOnQueryResult(closeOnQueryResult);
    }
}
