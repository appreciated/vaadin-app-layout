package com.github.appreciated.app.layout.component.menu.top.item;

import com.github.appreciated.app.layout.component.builder.interfaces.NavigationElement;
import com.github.appreciated.app.layout.component.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTab;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on instances
 * on {@link TopNavigationItem} respectively their {@link Component} which will usually causes a change of the View at the AppLayout content view.
 */
public class TopNavigationItem extends PaperTab implements NavigationElementContainer,
    NavigationElement {

    private NavigationElementContainer parent;

    public TopNavigationItem(String caption, Icon icon, Component view) {
        super();
        addNavigationElement(new TopNavigationLink(caption, icon, view.getClass()));
    }

    private void addNavigationElement(TopNavigationLink topNavigationLink) {
        add(topNavigationLink);
        applyParentToItem(topNavigationLink);
    }

    public TopNavigationItem(String caption, Icon icon, Class<? extends Component> className) {
        super();
        addNavigationElement(new TopNavigationLink(caption, icon, className));
    }

    @Override
    public void setNavigationElementContainer(NavigationElementContainer parent) {
        this.parent = parent;
    }

    @Override
    public void setActiveNavigationElement(NavigationElement component) {
        if (parent != null) {
            parent.setActiveNavigationElement(this);
        }
    }
}
