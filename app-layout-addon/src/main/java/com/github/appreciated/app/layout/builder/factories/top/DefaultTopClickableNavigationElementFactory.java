package com.github.appreciated.app.layout.builder.factories.top;

import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;

public class DefaultTopClickableNavigationElementFactory implements ComponentFactory<HasElement, ClickableNavigationElement> {
    @Override
    public HasElement get(ClickableNavigationElement element) {
        Button button = new Button(element.getName(), element.getIcon());
        button.addClickListener(buttonClickEvent -> element.getListener().onComponentEvent(null));
        return button;
    }
}
