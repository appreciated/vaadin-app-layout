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
import com.vaadin.flow.server.Command;

public class NotificationsView extends Composite<VerticalLayout> {

    boolean blurListenerEnabled = true;
    Holder<Boolean> showAll = new Holder<>(false);
    private NotificationHolder holder;
    private String noNotificationText = "No Notifications";
    private String showAllText = "Show all";
    private Button showAllButton;
    private Label noNotificationsLabel;
    private Command showAllCommand = () -> {
        showAll.value = true;
        initView();
    };

    public NotificationsView(NotificationHolder holder) {

        VerticalLayout content = getContent();

        content.setMargin(false);
        content.setPadding(false);
        content.setWidth("300px");
        getElement().getStyle().set("padding", "5px 10px 18px 8px");
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
                showAllButton = new Button(showAllText);
                showAllButton.setWidth("100%");
                showAllButton.setHeight("28px");
                showAllButton.addClickListener(clickEvent -> showAllCommand.execute());
                content.add(showAllButton);
            }
        } else {
            noNotificationsLabel = new Label(noNotificationText);
            noNotificationsLabel.getStyle().set("color", "var(--lumo-shade)")
                    .set("font-size", "var(--lumo-size-xxs)");
            HorizontalLayout wrapper = new HorizontalLayout(noNotificationsLabel);
            wrapper.setWidth("100%");
            wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            PaperCard card = new PaperCard(wrapper);
            card.setWidth("100%");
            card.getElement().getStyle().set("box-shadow", "var(--app-layout-notification-shadow)");
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

    public void setShowAllCommand(Command showAllCommand) {
        this.showAllCommand = showAllCommand;
    }

    public void setShowAllText(String showAllText) {
        this.showAllText = showAllText;
        if (this.showAllButton != null) {
            showAllButton.setText(showAllText);
        }
    }

    public void setNoNotificationText(String noNotificationText) {
        this.noNotificationText = noNotificationText;
        if (this.noNotificationsLabel != null) {
            noNotificationsLabel.setText(noNotificationText);
        }
    }

}
