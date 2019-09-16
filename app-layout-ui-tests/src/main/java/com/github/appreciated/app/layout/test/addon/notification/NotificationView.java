package com.github.appreciated.app.layout.test.addon.notification;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.test.addon.notification.view.*;
import com.github.appreciated.app.layout.test.base.AbstractLeftBehaviorBasicView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RoutePrefix;

@Push
@RoutePrefix(absolute = true, value = "notification")
public class NotificationView extends AbstractLeftBehaviorBasicView {
    @Override
    public Class<? extends AppLayout> getVariant() {
        return LeftLayouts.Left.class;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }

    @Override
    public void furtherConfiguration(AppLayoutBuilder builder) {
        DefaultNotificationHolder notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        NotificationButton<DefaultNotification> button = new NotificationButton<>(VaadinIcon.BELL, notificationHolder);
        button.getWrappedComponent().setId("it-test-notification-button");
        button.setId("it-test-notification-button-wrapper");
        notificationHolder.add(
                new DefaultNotification("Header 1", "Description 1"),
                new DefaultNotification("Header 2", "Description 2"),
                new DefaultNotification("Header 3", "Description 3"),
                new DefaultNotification("Header 4", "Description 4"),
                new DefaultNotification("Header 5", "Description 5")
        );
        getAppBar().add(button);
    }
}
