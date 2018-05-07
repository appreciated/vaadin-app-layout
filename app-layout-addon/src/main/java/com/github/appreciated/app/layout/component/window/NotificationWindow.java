package com.github.appreciated.app.layout.component.window;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.builder.entities.Holder;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collections;
import java.util.List;

public class NotificationWindow<T> extends Dialog {

    String height = "357px";
    boolean hasBlurListener = false;
    boolean blurListenerEnabled = true;
    Holder<Boolean> showAll = new Holder<>(false);
    Holder<Boolean> alignBottom = new Holder<>(false);
    private VerticalLayout notificationsView;
    private NotificationHolder holder;

    public NotificationWindow(NotificationHolder holder) {
        super();
        this.holder = holder;
        setWidth("300px");
        setHeight(height);
    }

    private VerticalLayout getCurrentView(NotificationHolder holder) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setMargin(false);
        wrapper.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_WINDOW);
        wrapper.setSizeFull();
        VerticalLayout panelWrapper = new VerticalLayout();
        panelWrapper.setHeight("100%");
        panelWrapper.setMargin(false);
        panelWrapper.setSpacing(false);

        VerticalLayout notificationsWrapper = new VerticalLayout();
        notificationsWrapper.setSpacing(false);
        notificationsWrapper.setMargin(true);
        new Button("", buttonClickEvent -> {
        });

        List<Component> components = holder.getNotifications(showAll.value);
        Collections.reverse(components);
        notificationsView = new VerticalLayout((Component[]) components.toArray());
        notificationsView.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);

        VerticalLayout panel = new VerticalLayout(notificationsWrapper);
        panelWrapper.add(panel);
        wrapper.add(panelWrapper);
        wrapper.setSpacing(false);

        if (alignBottom.value) {
            panelWrapper.setAlignItems(FlexComponent.Alignment.END);
        }
        if (showAll.value) {
            panel.setSizeFull();
        }
        if (!showAll.value && holder.getNotificationSize() > 4) {
            Button showAllButton = new Button("Show all");
            //showAllButton.getElement().getClassList().add(ValoTheme.BUTTON_BORDERLESS);
            // showAllButton.getElement().getClassList().add(ValoTheme.BUTTON_PRIMARY);
            showAllButton.setWidth("100%");
            showAllButton.setHeight("28px");
            showAllButton.addClickListener(clickEvent -> {
                showAll.value = true;
                showAllButton.setVisible(false);
                add(getCurrentView(holder));
            });
            wrapper.add(showAllButton);
        }
        notificationsWrapper.add(notificationsView);
        return wrapper;
    }

    public boolean isShowAll() {
        return this.showAll.value;
    }

    public void setShowAll(boolean showAll) {
        this.showAll.value = showAll;
    }

    public VerticalLayout getNotificationsView() {
        return notificationsView;
    }

    public void show(ClickEvent<Button> clickEvent) {
        show(clickEvent, true);
    }

    public void show(ClickEvent<Button> clickEvent, boolean addBelow) {
        /*
        if (!isAttached()) {
            if (addBelow) {
                if (UI.getCurrent().getPage().getBrowserWindowWidth() < clickEvent.getClientX() + 150) {
                    setPositionX(UI.getCurrent().getPage().getBrowserWindowWidth() - 300);
                } else {
                    setPositionX(clickEvent.getClientX() - 150);
                }
                setPositionY(clickEvent.getClientY() - clickEvent.getRelativeY() + 64);
            } else {
                if (UI.getCurrent().getPage().getBrowserWindowHeight() < clickEvent.getClientY() + height) {
                    setPositionY(UI.getCurrent().getPage().getBrowserWindowHeight() - height);
                    alignBottom.value = true;
                } else {
                    setPositionY(clickEvent.getClientY() - height);
                }
                if (clickEvent.getButton() instanceof NavigationButton) {
                    if (!AppLayoutSessionHelper.getActiveVariant().isSmall()) {
                        setPositionX(256);
                    } else {
                        setPositionX(64);
                    }
                } else {
                    setPositionX(256);
                }
            }
            removeAll();
            add(getCurrentView(holder));
            open();
            if (!hasBlurListener && blurListenerEnabled) {
                hasBlurListener = true;
                //getUI().ifPresent(ui -> ui.add.removeWindow(this));
            }
        } else {
            //UI.getCurrent().removeWindow(this);
        }*/
    }

    public void addNewNotification(NotificationHolder component) {
        add(getCurrentView(component));
    }

    public void setBlurListenerEnabled(boolean blurListener) {
        this.blurListenerEnabled = blurListener;
    }
}
