package com.github.appreciated.provider;

import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;


public class DefaultViewComponentProvider implements NavigationViewComponentProvider {
    @Override
    public Component getComponentForView(String caption, View view) {
        return getComponent(caption);
    }

    @Override
    public Component getComponentForViewClass(String caption, Class<? extends View> view) {
        return getComponent(caption);
    }

    Button getComponent(String caption) {
        Button button = new Button(caption);
        button.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.setWidth(100, Sizeable.Unit.PERCENTAGE);
        return button;
    }
}
