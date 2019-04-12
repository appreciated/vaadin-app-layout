package com.github.appreciated.app.layout.component.menu.top;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTab;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTabs;
import com.vaadin.flow.component.Component;

import java.util.Arrays;

public class TopMenuComponent extends PaperTabs implements NavigationElementContainer {

    public TopMenuComponent() {
        setHeight("100%");
    }

    @Override
    public void add(Component... components) {
        super.add(components);
        applyParentToItems(Arrays.stream(components));
    }

    @Override
    public void setActiveNavigationElement(NavigationElement active) {
        setSelected((PaperTab) active);
    }
}
