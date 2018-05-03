package com.github.appreciated.app.layout.builder.factories.top;

import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.component.button.NavigationButton;
import com.vaadin.flow.component.HasElement;
import com.vaadin.ui.themes.ValoTheme;

public class DefaultTopClickableNavigationElementFactory implements ComponentFactory<HasElement, ClickableNavigationElement> {
    @Override
    public HasElement get(ClickableNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.setWidthUndefined();
        button.addClickListener(element.getListener());
        return button;
    }
}
