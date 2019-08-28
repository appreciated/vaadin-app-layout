package com.github.appreciated.app.layout.search;

import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchView<T> extends IronOverlay {
    private final TextField searchField;
    private VerticalLayout results = new VerticalLayout();
    private VerticalLayout wrapper = new VerticalLayout();

    private DataViewProvider<T> dataViewProvider;
    private ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider;
    private Function<String, Query<T, SerializablePredicate<T>>> query;

    public SearchView() {
        setWithBackdrop(true);
        setVerticalAlign(VerticalOrientation.TOP);
        add(wrapper);
        searchField = new TextField();
        wrapper.add(searchField);
        searchField.addValueChangeListener(event -> {
            results.removeAll();
            List<T> result = dataProvider.fetch(query.apply(event.getValue())).collect(Collectors.toList());
            dataViewProvider.getViewsForResult(result.stream())
                    .forEach(results::add);
        });
        wrapper.add(results);
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.setWidthFull();
        getElement().getStyle()
                .set("width", "50vw")
                .set("height", "100%")
                .set("min-width", "400px")
                .set("max-width", "100vw");
        wrapper.setSizeFull();
        results.setSizeFull();
        results.setMargin(false);
        results.setPadding(false);
        results.getStyle().set("overflow", "auto");
    }

    public DataViewProvider<T> getDataViewProvider() {
        return dataViewProvider;
    }

    public void setDataViewProvider(DataViewProvider<T> dataViewProvider) {
        this.dataViewProvider = dataViewProvider;
    }

    public ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider, Function<String, Query<T, SerializablePredicate<T>>> query) {
        this.dataProvider = dataProvider;
        this.query = query;
    }

    public VerticalLayout getResults() {
        return results;
    }

    public VerticalLayout getWrapper() {
        return wrapper;
    }

    public TextField getSearchField() {
        return searchField;
    }
}
