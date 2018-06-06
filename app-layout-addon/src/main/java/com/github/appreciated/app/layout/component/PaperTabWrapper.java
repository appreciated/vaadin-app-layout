package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.paperbadge.PaperBadge;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTab;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;

public class PaperTabWrapper extends PaperTab implements NavigationElementComponent, NavigationElementContainer {

    private NavigatorNavigationElement element;

    public PaperTabWrapper(NavigatorNavigationElement element) {
        super(element.getCaption(), element.getIcon().getElement().getAttribute("icon"));
        this.element = element;
        if (element.getBadgeHolder() != null) {
            add(new PaperBadge(this));
        }
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
    public boolean setActiveNavigationElementWithViewClass(HasElement element) {
        if (this.element != null && this.element.getViewClassName() == element.getClass()) {
            setActive();
            return true;
        } else {
            return false;
        }
    }

}
