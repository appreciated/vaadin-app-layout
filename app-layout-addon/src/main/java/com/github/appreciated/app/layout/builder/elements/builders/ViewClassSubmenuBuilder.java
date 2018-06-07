package com.github.appreciated.app.layout.builder.elements.builders;

import com.vaadin.flow.component.icon.Icon;


/**
 * A Builder to build SubmenuNavigationElements
 */
public class ViewClassSubmenuBuilder<T extends ViewClassSubmenuBuilder> extends CDISubmenuBuilder<T> {

    protected ViewClassSubmenuBuilder(String title, Icon icon) {
        super(title, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static ViewClassSubmenuBuilder get(String title) {
        return new ViewClassSubmenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static ViewClassSubmenuBuilder get(Icon icon) {
        return new ViewClassSubmenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static ViewClassSubmenuBuilder get(String title, Icon icon) {
        return new ViewClassSubmenuBuilder(title, icon);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption  the caption of the menu element
     * @param viewName the url viewName under which the view will be available
     * @param element  This class name of the view
     * @return
     */
    // public T add(String caption, String viewName, Icon icon, Class<? extends Component> element) {
    //    return this.add(caption, viewName, icon, null, element);
    // }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption     the caption of the menu element
     * @param viewName    the url viewName under which the view will be available
     * @param badgeHolder
     * @param element     This class name of the view
     * @return
     */
    // public T add(String caption, String viewName, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends Component> element) {
    //     this.add(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, element));
    //   return (T) this;
    // }

}
