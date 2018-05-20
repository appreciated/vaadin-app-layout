package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;
import com.vaadin.flow.component.HasElement;

public class DefaultLeftClickableNavigationElementFactory implements ComponentFactory<HasElement, ClickableNavigationElement> {
    @Override
    public HasElement get(ClickableNavigationElement element) {
        PaperDrawerIconItem button = new PaperDrawerIconItem(element.getName(), "");
        button.getElement().getStyle().set("width", "100%");
        button.setClickListener(paperDrawerIconItemClickEvent -> element.getListener().onComponentEvent(paperDrawerIconItemClickEvent));
        return button;
    }
}
