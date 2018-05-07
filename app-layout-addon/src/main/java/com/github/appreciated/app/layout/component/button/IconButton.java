package com.github.appreciated.app.layout.component.button;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;

/**
 * A borderless icon-only {@link Button}.
 * 
 * FIXME I renamed it to IconButton. Because this is what this button is. Names should always reflect what the classes are and not
 * there they should be added or used.
 */
@SuppressWarnings("serial")
public class IconButton extends Button {

    /**
     * Creates a new {@link IconButton} with the default size (64px x 64px). The button is borderless and drawed as icon.
     * 
     * @param icon
     *            The icon to be used for the button
     */
    public IconButton(String icon) {
        super(icon);
        setWidth("64px");
        setHeight("64px");
        //getElement().getClassList().addAll(ValoTheme.BUTTON_BORDERLESS, ValoTheme.BUTTON_ICON_ONLY);
    }

    /**
     * Creates a new {@link IconButton} with the default size (64px x 64px). The button is borderless and drawed as icon.
     * 
     * @param icon
     *            The icon to be used for the button
     * @param listener
     *            The {@link ComponentEventListener} which will be added to the button
     */
    public IconButton(String icon, ComponentEventListener<ClickEvent<Button>> listener) {
        this(icon);
        addClickListener(listener);
    }
}
