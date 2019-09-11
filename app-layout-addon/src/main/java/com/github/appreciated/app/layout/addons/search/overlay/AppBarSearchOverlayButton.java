package com.github.appreciated.app.layout.addons.search.overlay;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.function.Consumer;
import java.util.function.Function;

public class AppBarSearchOverlayButton<T> extends Button {
    private final SearchOverlayView<T> searchView;

    public AppBarSearchOverlayButton() {
        this(VaadinIcon.SEARCH);
    }

    public AppBarSearchOverlayButton(VaadinIcon icon) {
        this(icon.create());
    }

    public AppBarSearchOverlayButton(Component icon) {
        super(icon);
        addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
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

    public SearchOverlayView<T> getSearchView() {
        return searchView;
    }

    public void setDataViewProvider(Function<T, ClickNotifier> provider) {
        this.searchView.setDataViewProvider(provider);
    }

    public void setDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider) {
        this.searchView.setDataProvider(dataProvider);
    }

    public void setQueryProvider(Function<String, Query<T, SerializablePredicate<T>>> query) {
        this.searchView.setQueryProvider(query);
    }

    public void setQueryResultListener(Consumer<T> queryResultListener) {
        this.searchView.setQueryResultListener(queryResultListener);
    }

    public void setCloseOnQueryResult(boolean closeOnQueryResult) {
        this.searchView.setCloseOnQueryResult(closeOnQueryResult);
    }
}
