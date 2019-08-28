package com.github.appreciated.app.layout.search;

import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    private final TextField searchField = new TextField();
    private final Button closeButton = new Button(VaadinIcon.CLOSE.create());
    private VerticalLayout results = new VerticalLayout();
    private HorizontalLayout searchFieldWrapper = new HorizontalLayout(searchField, closeButton);
    private VerticalLayout wrapper = new VerticalLayout(searchFieldWrapper, results);

    private DataViewProvider<T> dataViewProvider;
    private ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider;
    private Function<String, Query<T, SerializablePredicate<T>>> query;

    public SearchView() {
        setWithBackdrop(true);
        setVerticalAlign(VerticalOrientation.TOP);
        searchFieldWrapper.getStyle().set("margin-top", "calc(var(--lumo-space-m)*2)")
                .set("background", "var(--lumo-base-color)")
                .set("border-radius", "var(--lumo-border-radius)")
                .set("--lumo-primary-text-color", "var(--lumo-contrast-70pct)");
        searchFieldWrapper.setWidthFull();
        searchFieldWrapper.setSpacing(false);
        searchField.addValueChangeListener(event -> {
            results.removeAll();
            List<T> result = dataProvider.fetch(query.apply(event.getValue())).collect(Collectors.toList());
            dataViewProvider.getViewsForResult(result.stream())
                    .forEach(results::add);
        });
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();
        results.setSizeFull();
        results.setMargin(false);
        results.setPadding(false);
        results.getStyle().set("overflow", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> {
            searchField.clear();
            close();
        });
        wrapper.setSizeFull();
        wrapper.setMargin(false);
        wrapper.setPadding(false);
        wrapper.getStyle().set("max-width", "100vw");
        getElement().getStyle()
                .set("width", "30vw")
                .set("height", "100%")
                .set("min-width", "400px")
                .set("max-width", "100vw")
                .set("--lumo-size-m", "var(--lumo-size-xl)")
                .set("--lumo-contrast-10pct", "transparent");
        add(wrapper);
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
