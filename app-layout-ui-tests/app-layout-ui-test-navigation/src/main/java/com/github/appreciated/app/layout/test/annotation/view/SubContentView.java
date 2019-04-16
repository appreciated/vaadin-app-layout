package com.github.appreciated.app.layout.test.annotation.view;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.github.appreciated.app.layout.test.annotation.NavigationView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Caption("Inbox")
@Icon(VaadinIcon.INBOX)
@Route(value = "view2/subcontent", layout = NavigationView.class)
public class SubContentView extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}