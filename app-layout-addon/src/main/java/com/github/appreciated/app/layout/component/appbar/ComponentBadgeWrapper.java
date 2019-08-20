package com.github.appreciated.app.layout.component.appbar;

import com.github.appreciated.app.layout.webcomponents.paperbadge.PaperBadge;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class ComponentBadgeWrapper<T extends Component> extends HorizontalLayout {

    private final PaperBadge badge;
    private T wrappedComponent;

    public ComponentBadgeWrapper(T wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
        badge = new PaperBadge("0");
        badge.getElement().getStyle()
                .set("margin-top", "6px")
                .set("margin-left", "-6px");
        add(wrappedComponent);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(badge);
    }

    public void setBadgeCaption(String caption) {
        badge.setText(caption);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public T getWrappedComponent() {
        return wrappedComponent;
    }

    public HasText getBadge() {
        return badge;
    }
}


