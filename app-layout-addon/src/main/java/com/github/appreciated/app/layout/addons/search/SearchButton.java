package com.github.appreciated.app.layout.addons.search;

import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class SearchButton extends IconButton {
    private final SearchView searchView;

    public SearchButton() {
        this(VaadinIcon.SEARCH);
    }

    public SearchButton(VaadinIcon icon) {
        this(icon.create());
    }

    public SearchButton(Component icon) {
        super(icon);
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

    public SearchButton withValueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        addValueChangeListener(listener);
        return this;
    }

    public void addValueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        searchView.addValueChangeListener(listener);
    }
}
