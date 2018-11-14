package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.design.Styles;
import com.github.appreciated.app.layout.entity.Holder;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.webcomponents.papercard.PaperCard;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NotificationsView extends Composite<VerticalLayout> {

    boolean blurListenerEnabled = true;
    Holder<Boolean> showAll = new Holder<>(false);
    private NotificationHolder holder;

    public NotificationsView(NotificationHolder holder) {

        VerticalLayout content = getContent();

        content.setMargin(false);
        content.setPadding(false);
        content.setWidth("300px");
        getElement().getStyle().set("padding", "5px").set("--shadow-elevation-2dp_-_box-shadow", "0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2)");
        this.holder = holder;
        initView();
    }

    public void initView() {
        VerticalLayout content = getContent();

        content.removeAll();
        if (holder.getNotificationSize() > 0) {
			content.add(this.holder.getNotificationViews(showAll.value));
            getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_LIST);

            if (!showAll.value && this.holder.getNotificationSize() > 4) {
                Button showAllButton = new Button("Show all");
                showAllButton.setWidth("100%");
                showAllButton.setHeight("28px");
                showAllButton.addClickListener(clickEvent -> {
                    showAll.value = true;
                    initView();
                });
                content.add(showAllButton);
            }
        } else {
            Label label = new Label("No Notifications");
            label.getStyle().set("color", "var(--lumo-shade)")
                    .set("font-size", "var(--lumo-size-xxs)");
            HorizontalLayout wrapper = new HorizontalLayout(label);
            wrapper.setWidth("100%");
            wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            PaperCard card = new PaperCard(wrapper);
            card.setWidth("100%");
            content.add(card);
        }
    }

    public boolean isShowAll() {
        return this.showAll.value;
    }

    public void setShowAll(boolean showAll) {
        this.showAll.value = showAll;
    }

    public void refreshNotificationViews(NotificationHolder component) {
        initView();
    }

    public void setBlurListenerEnabled(boolean blurListener) {
        this.blurListenerEnabled = blurListener;
    }


}
