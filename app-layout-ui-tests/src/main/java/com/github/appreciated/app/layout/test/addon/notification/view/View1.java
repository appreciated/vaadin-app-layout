package com.github.appreciated.app.layout.test.addon.notification.view;

import com.github.appreciated.app.layout.test.addon.notification.NotificationView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = NotificationView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
