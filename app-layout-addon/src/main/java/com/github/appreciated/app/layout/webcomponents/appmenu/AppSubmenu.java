package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;


@Tag("app-submenu")
@HtmlImport("bower_components/app-menu/app-submenu.html")
public class AppSubmenu extends Component implements HasComponents, HasStyle, HasText {

    private final AppMenu menu;
    private final AppMenuIconItem item;
    private IronIcon ironIcon;
    private final Div toggleWrapper;
    private AppMenu parent;

    /**
     * <div class="app-menu-item" slot="submenu-trigger">
     * <app-menu-icon-item icon="icons:account-balance">Accounts</app-menu-icon-item>
     * <iron-icon icon="expand-more" class="expand-icon"></iron-icon>
     * </div>
     *
     * @param sectionName
     * @param icon
     */
    public AppSubmenu(String sectionName, Icon icon) {
        toggleWrapper = new Div();
        //toggleWrapper.getStyle().set("--expand-icon-fill-color", "var(--iron-icon-fill-color, none)");
        toggleWrapper.getClassNames().add("app-menu-item");
        toggleWrapper.getElement().setAttribute("slot", "submenu-trigger");
        toggleWrapper.getStyle().set("padding", "var(--app-layout-menu-button-padding)")
                .set("margin", "var(--app-layout-menu-button-margin)")
                .set("border-radius", "var(--app-layout-menu-button-border-radius)");

        item = new AppMenuIconItem(sectionName, icon.getElement().getAttribute("icon"));
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle().set("fill", "var(--expand-icon-fill-color)");
        toggleWrapper.add(item, ironIcon);
        menu = new AppMenu();

        menu.getElement().setAttribute("slot", "submenu-content");
        menu.getStyle()
                .set("border-radius", "var(--app-layout-menu-button-border-radius)");

        getElement().addSynchronizedPropertyEvent("app-submenu-open");
        getElement().addSynchronizedPropertyEvent("app-submenu-close");
        getElement().addSynchronizedProperty("opened")
                .addPropertyChangeListener("opened", event -> {
                    if ((Boolean) event.getValue())
                        ironIcon.getElement().setAttribute("icon", "icons:expand-less");
                    else
                        ironIcon.getElement().setAttribute("icon", "icons:expand-more");
                });

        addToSubmenu(toggleWrapper);
        addToSubmenu(menu);
    }

    @Override
    public String getText() {
        return item.getText();
    }

    @Override
    public void setText(String text) {
        item.setText(text);
    }

    public Div getToggleWrapper() {
        return toggleWrapper;
    }

    private void addToSubmenu(Component component) {
        getElement().appendChild(component.getElement());
    }

    @Override
    public void add(Component... components) {
        menu.add(components);
    }

    @Override
    public void remove(Component... components) {
        menu.remove(components);
    }

    @Override
    public void removeAll() {
        menu.removeAll();
    }

    public AppMenu getMenu() {
        return menu;
    }

    public void setParent(AppMenu parent) {
        this.parent = parent;
        menu.setParent(this);
    }

    public AppMenuIconItem getItem() {
        return item;
    }

    public IronIcon getIronIcon() {
        return ironIcon;
    }

    public void setSelected() {
        this.parent.setSelected(this);
    }
}

