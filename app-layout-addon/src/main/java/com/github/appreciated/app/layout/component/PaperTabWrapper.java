package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTab;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.Icon;

public class PaperTabWrapper extends PaperTab implements NavigationElementComponent {

    private NavigatorNavigationElement element;

    public PaperTabWrapper(NavigatorNavigationElement element) {
        super(element.getCaption(), element.getIcon().getElement().getAttribute("icon"), element.getBadgeHolder());
        this.element = element;
    }


    public PaperTabWrapper(String caption, Icon icon, DefaultBadgeHolder badgeHolder) {
        super(caption, icon.getElement().getAttribute("icon"), badgeHolder);
    }

    @Override
    public void setNavigationIcon(String resource) {

    }

    @Override
    public void setNavigationCaption(String string) {

    }

    @Override
    public void setClickListener(ComponentEventListener listener) {
        getElement().addEventListener("click", domEvent -> listener.onComponentEvent(null));
    }

    @Override
    public Class getNavigationViewClass() {
        return element != null ? element.getViewClassName() : null;
    }

    @Override
    public void setAsActiveNavigation(boolean b) {
    }

}
