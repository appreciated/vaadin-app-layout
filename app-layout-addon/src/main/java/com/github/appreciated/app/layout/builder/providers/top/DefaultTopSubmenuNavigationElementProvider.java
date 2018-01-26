package com.github.appreciated.app.layout.builder.providers.top;

import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import java.io.Serializable;

public class DefaultTopSubmenuNavigationElementProvider implements Serializable, ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> {
    @Override
    public SubmenuNavigationElement.SubmenuComponent get(SubmenuNavigationElement element) {
        SubMenuBar barmenu = new SubMenuBar();
        barmenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        barmenu.addStyleName(ValoTheme.MENUBAR_SMALL);
        barmenu.addStyleName("app-layout-top-menu");
        addToMenuBar(barmenu, element);
        return barmenu;
    }

    private void addToMenuBar(MenuBar menuBar, AbstractNavigationElement element) {
        if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement submenu = (SubmenuNavigationElement) element;
            MenuBar.MenuItem submenuItem = menuBar.addItem(submenu.getTitle(), submenu.getIcon(), null);
            submenu.getSubmenuElements().forEach(element1 -> {
                addToMenuBar(submenuItem, element1);
            });
        }
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement submenu = (NavigatorNavigationElement) element;
            menuBar.addItem(submenu.getCaption(), submenu.getIcon(), menuItem -> UI.getCurrent().getNavigator().navigateTo(submenu.getViewName()));
        }
        if (element instanceof ClickableNavigationElement) {
            ClickableNavigationElement submenu = (ClickableNavigationElement) element;
            menuBar.addItem(submenu.getName(), submenu.getIcon(), menuItem -> submenu.getListener().buttonClick(null));
        }
    }

    private void addToMenuBar(MenuBar.MenuItem menuBar, AbstractNavigationElement element) {
        if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement submenu = (SubmenuNavigationElement) element;
            MenuBar.MenuItem submenuItem = menuBar.addItem(submenu.getTitle(), submenu.getIcon(), null);
            submenu.getSubmenuElements().forEach(element1 -> {
                addToMenuBar(submenuItem, element1);
            });
        }
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement submenu = (NavigatorNavigationElement) element;
            menuBar.addItem(submenu.getCaption(), submenu.getIcon(), menuItem -> UI.getCurrent().getNavigator().navigateTo(submenu.getViewName()));
        }
        if (element instanceof ClickableNavigationElement) {
            ClickableNavigationElement submenu = (ClickableNavigationElement) element;
            menuBar.addItem(submenu.getName(), submenu.getIcon(), menuItem -> submenu.getListener().buttonClick(null));
        }
    }

    public class SubMenuBar extends MenuBar implements SubmenuNavigationElement.SubmenuComponent {
    }
}