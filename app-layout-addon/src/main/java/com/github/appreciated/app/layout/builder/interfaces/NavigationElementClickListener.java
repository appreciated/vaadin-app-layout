package com.github.appreciated.app.layout.builder.interfaces;

import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.vaadin.ui.Button;

public interface NavigationElementClickListener {
    void onClick(AbstractNavigationElement element, Button.ClickEvent event);
}
