package com.github.appreciated.app.layout.addons.search.overlay;

import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchOverlayView<T> extends IronOverlay {
    private final TextField searchField = new TextField();
    private final IconButton closeButton = new IconButton(VaadinIcon.ARROW_LEFT.create());
    private VerticalLayout results = new VerticalLayout();
    private HorizontalLayout searchFieldWrapper = new HorizontalLayout(closeButton, searchField);
    private VerticalLayout wrapper = new VerticalLayout(searchFieldWrapper, results);

    private Function<T, ClickNotifier> dataViewProvider;
    private ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider;
    private Function<String, Query<T, SerializablePredicate<T>>> queryProvider;

    private Consumer<T> queryResultListener;
    private boolean closeOnQueryResult = true;

    public SearchOverlayView() {
        getElement().getStyle().set("width", "100%");
        setVerticalAlign(VerticalOrientation.TOP);
        searchFieldWrapper.getStyle()
                .set("background", "var(--app-layout-bar-background-base-color)")
                .set("height", "var(--app-bar-height)")
                .set("box-shadow", "var(--app-layout-bar-shadow)")
                .set("padding", "var(--app-layout-bar-padding)")
                .set("z-index", "1");
        searchFieldWrapper.setWidthFull();
        searchFieldWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        searchField.getStyle().set("--lumo-contrast-10pct", "transparent");
        searchField.addValueChangeListener(event -> {
            results.removeAll();
            List<T> result = dataProvider.fetch(queryProvider.apply(event.getValue())).collect(Collectors.toList());
            result.stream()
                    .map(t -> new QueryPair<>(t, dataViewProvider.apply(t)))
                    .forEach(clickNotifier -> {
                        results.add((Component) clickNotifier.getNotifier());
                        clickNotifier.getNotifier().addClickListener(clickEvent -> {
                            if (closeOnQueryResult) {
                                this.close();
                            }
                            if (queryResultListener != null) {
                                queryResultListener.accept(clickNotifier.getQuery());
                            }
                        });
                    });
        });
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setWidthFull();
        results.setSizeFull();
        results.setMargin(false);
        results.getStyle().set("overflow", "auto");
        closeButton.addClickListener(event -> {
            searchField.clear();
            close();
        });
        wrapper.setSizeFull();
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setMargin(false);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.getStyle()
                .set("max-width", "100vw")
                .set("height", "100vh");
        results.getStyle()
                .set("overflow-y", "auto")
                .set("max-width", "100%")
                .set("min-width", "40%")
                .set("--lumo-size-m", "var(--lumo-size-xl)")
                .set("--lumo-contrast-10pct", "transparent");
        results.setHeightFull();
        results.setWidth("unset");
        add(wrapper);
    }

    @Override
    public void open() {
        super.open();
        searchField.focus();
    }

    public Function<T, ClickNotifier> getDataViewProvider() {
        return dataViewProvider;
    }

    public void setDataViewProvider(Function<T, ClickNotifier> dataViewProvider) {
        this.dataViewProvider = dataViewProvider;
    }

    public ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(ConfigurableFilterDataProvider<T, SerializablePredicate<T>, SerializablePredicate<T>> dataProvider) {
        this.dataProvider = dataProvider;
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

    public void setQueryProvider(Function<String, Query<T, SerializablePredicate<T>>> queryProvider) {
        this.queryProvider = queryProvider;
    }

    public void setQueryResultListener(Consumer<T> queryResultListener) {
        this.queryResultListener = queryResultListener;
    }

    public void setCloseOnQueryResult(boolean closeOnQueryResult) {
        this.closeOnQueryResult = closeOnQueryResult;
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
