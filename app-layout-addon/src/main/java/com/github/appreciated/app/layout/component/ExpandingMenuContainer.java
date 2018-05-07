package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.button.NavigationButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.*;

/**
 * The component which is used for submenu elements. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer extends VerticalLayout implements SubmenuNavigationElement.SubmenuElement {

    private final VerticalLayout submenuWrapper;

    public ExpandingMenuContainer(String sectionName, Icon icon) {
        getElement().getClassList().add(EXPANDING_MENU_CONTAINER_PRIMARY_STYLE);
        setMargin(false);
        NavigationButton expandMenuButton = new NavigationButton(sectionName, icon);
        expandMenuButton.addClickListener(clickEvent -> {
            if (getElement().getClassList().contains(EXPANDING_MENU_CONTAINER_OPEN)) {
                getElement().getClassList().remove(EXPANDING_MENU_CONTAINER_OPEN);
            } else {
                getElement().getClassList().add(EXPANDING_MENU_CONTAINER_OPEN);
            }
        });
        HorizontalLayout buttonWrapper = new HorizontalLayout(expandMenuButton);
        buttonWrapper.getElement().getClassList().add(EXPANDING_MENU_CONTAINER_BUTTON);
        buttonWrapper.setSpacing(false);
        super.add(buttonWrapper);
        buttonWrapper.setWidth("100%");

        submenuWrapper = new VerticalLayout();
        submenuWrapper.getElement().getClassList().add(EXPANDING_MENU_SUBMENU_CONTAINER);
        submenuWrapper.setMargin(false);
        submenuWrapper.setWidth("100%");

        super.add(buttonWrapper);
        super.add(submenuWrapper);
    }

    @Override
    public void add(Component... components) {
        submenuWrapper.add(components);
    }

    @Override
    public void close() {
        if (getElement().getClassList().contains(EXPANDING_MENU_CONTAINER_OPEN)) {
            getElement().getClassList().remove(EXPANDING_MENU_CONTAINER_OPEN);
        }
    }
}
