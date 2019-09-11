package com.github.appreciated.app.layout.test.addon.profile.view;

import com.github.appreciated.app.layout.test.addon.profile.ProfileView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = ProfileView.class) // an empty view name will also be the default view
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
