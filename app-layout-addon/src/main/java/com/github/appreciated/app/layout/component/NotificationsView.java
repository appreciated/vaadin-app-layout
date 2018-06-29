package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.builder.entities.Holder;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.webcomponents.papercard.PaperCard;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NotificationsView extends VerticalLayout {

    boolean blurListenerEnabled = true;
    Holder<Boolean> showAll = new Holder<>(false);
    private NotificationHolder holder;

    public NotificationsView(NotificationHolder holder) {
        super();
        setMargin(false);
        setPadding(false);
        setWidth("300px");
        getElement().getStyle().set("padding", "5px");

        this.holder = holder;
        initView();
    }

    public void initView() {
        removeAll();
        if (holder.getNotificationSize() > 0) {
            add(this.holder.getNotificationViews(false));
            getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_LIST);

            if (!showAll.value && this.holder.getNotificationSize() > 4) {
                Button showAllButton = new Button("Show all");
                showAllButton.setWidth("100%");
                showAllButton.setHeight("28px");
                showAllButton.addClickListener(clickEvent -> {
                    showAll.value = true;
                    initView();
                });
                add(showAllButton);
            }
        } else {
            Label label = new Label("No Notifications");
            label.getElement().getStyle().set("color", "var(--lumo-shade)");
            label.getElement().getStyle().set("font-size", "var(--lumo-size-xxs)");
            HorizontalLayout wrapper = new HorizontalLayout(label);
            wrapper.setWidth("100%");
            wrapper.setJustifyContentMode(JustifyContentMode.CENTER);
            PaperCard card = new PaperCard(wrapper);
            card.setWidth("100%");
            add(card);
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
