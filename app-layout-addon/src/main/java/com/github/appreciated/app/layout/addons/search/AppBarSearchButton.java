package com.github.appreciated.app.layout.addons.search;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class AppBarSearchButton extends Button {
    private final SearchView searchView;

    public AppBarSearchButton() {
        this(VaadinIcon.SEARCH);
    }

    public AppBarSearchButton(VaadinIcon icon) {
        this(icon.create());
    }

    public AppBarSearchButton(Component icon) {
        super(icon);
        addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
        searchView = new SearchView();
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

    public AppBarSearchButton withValueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        addValueChangeListener(listener);
        return this;
    }

    public void addValueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        searchView.addValueChangeListener(listener);
    }
}
