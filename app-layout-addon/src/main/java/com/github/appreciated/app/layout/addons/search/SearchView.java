package com.github.appreciated.app.layout.addons.search;

import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class SearchView extends IronOverlay {
    private final TextField searchField = new TextField();
    private final Button closeButton = new Button(VaadinIcon.ARROW_LEFT.create());
    private HorizontalLayout searchFieldWrapper = new HorizontalLayout(closeButton, searchField);

    public SearchView() {
        getElement().getStyle().set("width", "100%");
        setVerticalAlign(VerticalOrientation.TOP);
        setWithBackdrop(false);
        searchFieldWrapper.getStyle()
                .set("background", "var(--app-layout-bar-background-base-color)")
                .set("height", "var(--app-bar-height)")
                .set("box-shadow", "var(--app-layout-bar-shadow)")
                .set("padding", "var(--app-layout-bar-padding)")
                .set("z-index", "1");
        searchFieldWrapper.setWidthFull();
        searchFieldWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        searchFieldWrapper.setSpacing(false);
        searchField.getStyle().set("--lumo-contrast-10pct", "transparent");
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setWidthFull();
        closeButton.setWidth("var(--app-bar-height)");
        closeButton.setHeight("var(--app-bar-height)");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> {
            searchField.clear();
            close();
        });
        add(searchFieldWrapper);
    }

    @Override
    public void open() {
        super.open();
        searchField.focus();
    }

    public TextField getSearchField() {
        return searchField;
    }
}
