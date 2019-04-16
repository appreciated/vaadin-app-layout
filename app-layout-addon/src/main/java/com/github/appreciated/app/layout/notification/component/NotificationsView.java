package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.design.Styles;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.card.Card;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.Command;

public class NotificationsView extends Composite<VerticalLayout> {

  private Boolean showAll = Boolean.FALSE;
  private NotificationHolder holder;
  private String noNotificationText = "No Notifications";
  private String showAllText = "Show all";
  private Button showAllButton;
  private Label noNotificationsLabel;
  private Command showAllCommand = () -> {
    showAll = Boolean.TRUE;
    initView();
  };

  public NotificationsView(NotificationHolder holder) {

    VerticalLayout content = getContent();

    content.setMargin(false);
    content.setPadding(false);
      content.setWidth("var(--notification-width)");
      getElement().getStyle().set("padding", "5px 2px 18px 8px");
    this.holder = holder;
    initView();
  }

  void initView() {
    VerticalLayout content = getContent();

    content.removeAll();
    if (holder.getNotificationSize() > 0) {
      content.add(this.holder.getNotificationViews(showAll));
      getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_LIST);

      if (! showAll && this.holder.getNotificationSize() > 4) {
        showAllButton = new Button(showAllText);
        showAllButton.setWidth("100%");
        showAllButton.setHeight("28px");
        showAllButton.addClickListener(clickEvent -> showAllCommand.execute());
        content.add(showAllButton);
      }
    } else {
      noNotificationsLabel = new Label(noNotificationText);
      noNotificationsLabel.getStyle().set("color" , "var(--app-layout-notification-font-color)")
                          .set("font-size" , "var(--app-layout-font-size-menu)");
      HorizontalLayout wrapper = new HorizontalLayout(noNotificationsLabel);
      wrapper.setWidth("100%");
      wrapper.setPadding(true);
      wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
      Card card = new Card(wrapper);
      card.getTemplateDiv().getStyle().set("background","var(--app-layout-notification-background-color)");
      card.setWidth("100%");
      content.add(card);
    }
  }

  public boolean isShowAll() {
    return this.showAll;
  }

  public void setShowAll(boolean showAll) {
    this.showAll = showAll;
  }

  public void refreshNotificationViews(NotificationHolder component) {
    initView();
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
