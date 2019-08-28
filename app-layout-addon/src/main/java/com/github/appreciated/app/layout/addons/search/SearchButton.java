package com.github.appreciated.app.layout.addons.search;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.function.Function;

public class SearchButton<T> extends Button {
    private final SearchView<T> searchView;

    public SearchButton() {
        this(VaadinIcon.SEARCH);
    }

    public SearchButton(VaadinIcon icon) {
        this(icon.create());
    }

    public SearchButton(Component icon) {
        super(icon);
        addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
        searchView = new SearchView<>();
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

    public void setDataViewProvider(DataViewProvider<T> provider) {
        this.searchView.setDataViewProvider(provider);
    }

    public void setDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider, Function<String, Query<T, SerializablePredicate<T>>> query) {
        this.searchView.setDataProvider(dataProvider, query);
    }

    public SearchView<T> getSearchView() {
        return searchView;
    }
}
