package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.PairComponentProvider;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.component.RoundResourceButton;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class DefaultNotificationCompontProvider implements PairComponentProvider<NotificationHolder<DefaultNotification>, DefaultNotification> {
    @Override
    public Component getComponent(NotificationHolder holder, DefaultNotification info) {
        VerticalLayout wrapper = new VerticalLayout();
        Label title = new Label(info.getTitle());
        title.addStyleName(ValoTheme.LABEL_BOLD);
        title.addStyleName(ValoTheme.LABEL_COLORED);
        Label description = new Label(info.getDescription());

        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);
        if (info.getImage() != null) {
            RoundResourceButton image = new RoundResourceButton(info.getImage());
            descriptionWrapper.addComponent(image);
            descriptionWrapper.setExpandRatio(description, 1.0f);
        }
        wrapper.addComponent(title);
        wrapper.addComponent(descriptionWrapper);
        wrapper.addLayoutClickListener(layoutClickEvent -> holder.onNotificationClicked(info));
        return wrapper;
    }
}
