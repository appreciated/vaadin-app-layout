package com.github.appreciated.applayout.component.appmenu;

import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.applayout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.applayout.webcomponents.appmenu.AppMenuItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

public class NavigationBadgeIconButton extends AppMenuItem implements NavigationElementContainer {

    static int idCounter = 0;

    private final MenuBadgeComponent badge;
    private LeftNavigationComponent element;

    public NavigationBadgeIconButton(LeftNavigationComponent element) {
        this(element.getCaption(), element.getIcon(), null);
        this.element = element;
    }

    public NavigationBadgeIconButton(String name, Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        super(name, icon.getElement().getAttribute("icon"));
        setId("menu-btn-" + idCounter++);
        badge = new MenuBadgeComponent();
        badge.setVisible(false);
        add(badge);
        if (listener != null) {
            setClickListener(appMenuIconItemClickEvent -> listener.onComponentEvent(null));
        }
        getItem().getElement().getStyle().set("white-space", "nowrap");
    }

    public NavigationBadgeIconButton(LeftClickableComponent element) {
        this(element.getName(), element.getIcon(), null);
    }

    private void setBadgeCaption(String status) {
        badge.setText(status);
    }

    @Override
    public void setClickListener(ComponentEventListener listener) {
        super.setClickListener(listener);
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
