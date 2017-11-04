package com.github.appreciated.app.layout.builder.providers.top;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.component.NavigationButton;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;


public class DefaultTopCustomNavigationElementProvider implements ComponentProvider<Component, ClickableNavigationElement> {
    @Override
    public Component get(ClickableNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.setWidthUndefined();
        button.addClickListener(element.getListener());
        return button;
    }
}
