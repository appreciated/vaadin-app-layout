package com.github.appreciated.app.layout.test.addon.notification;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.test.addon.notification.view.*;
import com.github.appreciated.app.layout.test.base.AbstractLeftBehaviorBasicView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "notification")
public class NotificationView extends AbstractLeftBehaviorBasicView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT;
    }

    @Override
    public Class<? extends Component>[] getViews() {
        return new Class[]{View1.class, View2.class, View3.class, View4.class, View5.class, View6.class, View7.class, View8.class, View9.class};
    }

    @Override
    public void furtherConfiguration(AppLayoutBuilder builder) {
        DefaultNotificationHolder notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        notificationHolder.addNotification(new DefaultNotification("Header 1", "Description 1"));
        notificationHolder.addNotification(new DefaultNotification("Header 2", "Description 2"));
        notificationHolder.addNotification(new DefaultNotification("Header 3", "Description 3"));
        notificationHolder.addNotification(new DefaultNotification("Header 4", "Description 4"));
        notificationHolder.addNotification(new DefaultNotification("Header 5", "Description 5"));
        builder.withAppBar(new AppBarNotificationButton<>(VaadinIcon.BELL, notificationHolder));
    }
}
