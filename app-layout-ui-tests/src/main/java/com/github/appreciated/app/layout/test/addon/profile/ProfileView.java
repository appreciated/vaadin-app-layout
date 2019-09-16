package com.github.appreciated.app.layout.test.addon.profile;

import com.github.appreciated.app.layout.addons.profile.ProfileButton;
import com.github.appreciated.app.layout.addons.profile.builder.AppBarProfileButtonBuilder;
import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.test.addon.profile.view.*;
import com.github.appreciated.app.layout.test.base.AbstractLeftBehaviorBasicView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RoutePrefix;

@RoutePrefix(absolute = true, value = "profile")
public class ProfileView extends AbstractLeftBehaviorBasicView {
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
        ProfileButton button = AppBarProfileButtonBuilder.get()
                .withItem("Profile Entry 1", event -> Notification.show("Profile Entry 1 clicked"))
                .withItem("Profile Entry 2", event -> Notification.show("Profile Entry 2 clicked"))
                .withItem("Profile Entry 3", event -> Notification.show("Profile Entry 3 clicked"))
                .build();
        button.getWrappedComponent().setId("it-test-profile-button");
        builder.withAppBar(button);
    }
}
