package com.github.appreciated.app.layout.component.menu.left.items;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.HighlightAction;
import com.vaadin.flow.router.RouterLink;

import java.util.Optional;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that
 * clicks on instances on {@link LeftNavigationItem} respectively their
 * {@link com.vaadin.flow.component.Component} which will usually causes a change of the View at the
 * AppLayout content view.
 */
public class LeftNavigationItem extends LeftBadgeIconItem implements NavigationElementComponent {
    /**
     * The caption of this menu element
     */
    private String caption;

    /**
     * The view behind this menu element
     */
    private Icon icon;

    private Optional<NavigationElementContainer> parent = Optional.empty();

    public LeftNavigationItem(String caption, Icon icon, Component view) {
        this(caption, icon, view.getClass());
        this.caption = caption;
        this.icon = icon;
    }

    public LeftNavigationItem(String caption, Icon icon, Class<? extends Component> className) {
        super(caption, icon);
        this.caption = caption;
        this.icon = icon;
        setRoute(UI.getCurrent().getRouter(), className);
        setHighlightCondition((routerLink, event) ->
               getHref().equals(event.getLocation().getPath())
        );
        HighlightAction<RouterLink> action = getHighlightAction();

        setHighlightAction((routerLink, highlight) -> {
            action.highlight(routerLink, highlight);
            System.out.println(routerLink.getHref() + " : " + highlight);
            parent.ifPresent(container -> container.setActiveNavigationElement(highlight));
        });
    }

    public LeftNavigationItem(String caption, VaadinIcon icon, Class<? extends Component> className) {
        this(caption, icon.create(), className);
    }

    public LeftNavigationItem(Component view) {
        this(view.getClass());
    }

    public LeftNavigationItem(Class<? extends Component> className) {
        this(className.getAnnotation(Caption.class).value(),
                className.getAnnotation(com.github.appreciated.app.layout.annotations.Icon.class).value().create(),
                className
        );
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setNavigationElementContainer(NavigationElementContainer parent) {
        this.parent = Optional.of(parent);
    }

}
