package com.github.appreciated.app.layout.test.base;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("styles.css")
public abstract class AbstractLeftBehaviorView extends AppLayoutRouterLayout {
    DefaultNotificationHolder notificationHolder;
    DefaultBadgeHolder badgeHolder;
    private Thread currentThread;

    public abstract Class<? extends AppLayout> getVariant();

    protected Class<? extends Component> getViewForI(int i) {
        return getViews()[i - 1];
    }

    public abstract Class<? extends Component>[] getViews();

    public void furtherConfiguration(AppLayoutBuilder builder) {
    }
}
