package com.github.appreciated.app.layout.test.addon.profile.view;

import com.github.appreciated.app.layout.test.addon.profile.ProfileView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = ProfileView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
