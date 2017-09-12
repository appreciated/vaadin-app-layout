package com.github.appreciated.provider;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;


public class DefaultViewComponentProvider implements NavigationViewComponentProvider {
    @Override
    public Component getComponentForView(String caption, View view) {
        Button button = new Button(caption);
        button.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        return button;
    }
}
