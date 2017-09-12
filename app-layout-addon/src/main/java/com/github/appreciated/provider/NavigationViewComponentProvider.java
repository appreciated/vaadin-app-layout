package com.github.appreciated.provider;


import com.vaadin.navigator.View;
import com.vaadin.ui.Component;


public interface NavigationViewComponentProvider {
    Component getComponentForView(String caption, View view);

    Component getComponentForViewClass(String caption, Class<? extends View> view);
}
