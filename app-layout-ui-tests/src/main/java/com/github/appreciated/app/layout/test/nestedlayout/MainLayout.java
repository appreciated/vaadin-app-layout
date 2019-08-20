package com.github.appreciated.app.layout.test.nestedlayout;

import com.github.appreciated.app.layout.test.nestedlayout.view.View5;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;

/**
 * Created by Intellij IDEA.
 * Date: 27. 2. 2019 12:26
 *
 * @author Gabriel Gecy
 */
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div implements RouterLayout, BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget().equals(View5.class)) {
            event.rerouteToError(AccessDeniedException.class, "Unauthorized view access");
        }
    }
}
