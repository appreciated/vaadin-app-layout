package com.github.appreciated.app.layout.component.window;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.Holder;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.component.button.NavigationButton;
import com.github.appreciated.app.layout.session.AppLayoutSessionHelper;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collections;
import java.util.List;

public class NotificationWindow<T> extends Window {

    int height = 357;
    boolean hasBlurListener = false;
    boolean blurListenerEnabled = true;
    Holder<Boolean> showAll = new Holder<>(false);
    Holder<Boolean> alignBottom = new Holder<>(false);
    private VerticalLayout notificationsView;
    private NotificationHolder holder;

    public NotificationWindow(NotificationHolder holder) {
        super();
        this.holder = holder;
        setWidth(300, Sizeable.Unit.PIXELS);
        setHeight(height, Unit.PIXELS);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
    }

    private VerticalLayout getCurrentView(NotificationHolder holder) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setMargin(false);
        wrapper.addStyleName(Styles.APP_BAR_NOTIFICATION_WINDOW);
        wrapper.setSizeFull();
        VerticalLayout panelWrapper = new VerticalLayout();
        panelWrapper.setHeight(100, Unit.PERCENTAGE);
        panelWrapper.setMargin(false);
        panelWrapper.setSpacing(false);

        VerticalLayout notificationsWrapper = new VerticalLayout();
        notificationsWrapper.setSpacing(false);
        notificationsWrapper.setMargin(true);

        List<Component> components = holder.getNotifications(showAll.value);
        Collections.reverse(components);
        notificationsView = new VerticalLayout(components.toArray(new Component[]{}));
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);

        Panel panel = new Panel(notificationsWrapper);
        panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        panelWrapper.addComponent(panel);
        wrapper.addComponent(panelWrapper);
        wrapper.setSpacing(false);
        wrapper.setExpandRatio(panelWrapper, 1.0f);
        if (alignBottom.value) {
            panelWrapper.setComponentAlignment(panel, Alignment.BOTTOM_LEFT);
        }
        if (showAll.value) {
            panel.setSizeFull();
        }
        if (!showAll.value && holder.getNotificationSize() > 4) {
            Button showAllButton = new Button("Show all");
            showAllButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            showAllButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
            showAllButton.setWidth(100, Unit.PERCENTAGE);
            showAllButton.setHeight(28, Unit.PIXELS);
            showAllButton.addClickListener(clickEvent -> {
                showAll.value = true;
                showAllButton.setVisible(false);
                setContent(getCurrentView(holder));
            });
            wrapper.addComponent(showAllButton);
        }
        notificationsWrapper.addComponent(notificationsView);
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

    public void show(Button.ClickEvent clickEvent) {
        show(clickEvent, true);
    }

    public void show(Button.ClickEvent clickEvent, boolean addBelow) {
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
            setContent(getCurrentView(holder));
            UI.getCurrent().addWindow(this);
            focus();
            if (!hasBlurListener && blurListenerEnabled) {
                hasBlurListener = true;
                getUI().addClickListener(event -> UI.getCurrent().removeWindow(this));
            }
        } else {
            UI.getCurrent().removeWindow(this);
        }
    }

    public void addNewNotification(NotificationHolder component) {
        setContent(getCurrentView(component));
    }

    public void setBlurListenerEnabled(boolean blurListener) {
        this.blurListenerEnabled = blurListener;
    }
}
