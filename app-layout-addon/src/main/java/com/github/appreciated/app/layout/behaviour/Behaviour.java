package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.behaviour.left.*;
import com.github.appreciated.app.layout.behaviour.left.*;
import com.github.appreciated.app.layout.behaviour.top.Top;
import com.github.appreciated.app.layout.behaviour.top.TopLarge;

/**
 * Contains all the available Behaviours and their specific details {@link Behaviour#Behaviour(Class, boolean, boolean, boolean, boolean)}
 */
public enum Behaviour {
    /**
     * The default behaviour. An instance of the following {@link Behaviour} can always be collapsed manually with the hamburger button.
     * The menu position is on the left beneath the app-bar.
     */
    LEFT(Left.class, false, true, false, false),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * The menu position is on the left beneath the app-bar.
     */
    LEFT_RESPONSIVE(LeftResponsive.class, false, true, false, false),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to use more space the menu can be toggle manually toggled between a smaller and a larger state.
     * The menu position is on the left beneath the app-bar.
     * The initial state is always the larger menu.
     */
    LEFT_HYBRID(LeftHybrid.class, false, true, false, false),

    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to use more space the menu can be toggle manually toggled between a smaller and a larger state.
     * The menu position is on the left beneath the app-bar.
     * The initial state is always the smaller menu.
     */
    LEFT_HYBRID_SMALL(LeftHybridSmall.class, false, true, false, true),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to be shown better the menu will be collapsed at a certain width range to a smaller in between state before it will be hidden completly.
     * The menu position is on the left beneath the app-bar.
     */
    LEFT_RESPONSIVE_HYBRID(LeftResponsiveHybrid.class, false, true, false, false),
    /**
     * An instance of the following {@link Behaviour} can be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to be shown better the menu will be collapsed at a certain width range to a smaller in between state before it will be hidden completly.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} is positioned on the left. If the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} is visible it will be shown beside on the right of the {@link com.github.appreciated.applayout.webcomponents.appmenu.AppMenu}.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} will be hidden unless a certain device width is surpassed to allow to show the button to show the menu.
     */
    LEFT_RESPONSIVE_HYBRID_NO_APP_BAR(LeftResponsiveHybridNoAppBar.class, false, false, false, false),
    /**
     * An instance of the following {@link Behaviour} can be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to be shown better the menu will be collapsed at a certain width range to a smaller in between state before it will be hidden completly.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} is positioned on the left. If the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} is visible it will be shown beside on the right of the {@link com.github.appreciated.applayout.webcomponents.appmenu.AppMenu}.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} will be hidden unless a certain device width is surpassed to allow to show the button to show the menu.
     */
    LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR(LeftResponsiveHybridOverlayNoAppBar.class, false, false, false, false),
    /**
     * An instance of the following {@link Behaviour} can be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to be shown better the menu will be collapsed at a certain width range to a smaller in between state before it will be hidden completly.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} is positioned on the left. The {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} will be shown beside on the right of the {@link com.github.appreciated.applayout.webcomponents.appmenu.AppMenu}.
     */
    LEFT_OVERLAY(LeftOverlay.class, false, true, true, false),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * The menu position is on the left beside the app-bar.
     */
    LEFT_RESPONSIVE_OVERLAY(LeftResponsiveOverlay.class, false, true, true, false),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * The menu position is on the left beside the app-bar.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} is positioned on the left. If the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} is visible it will be shown beside on the right of the {@link com.github.appreciated.applayout.webcomponents.appmenu.AppMenu}.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} will be hidden unless a certain device width is surpassed to allow to show the button to show the menu.
     */
    LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR(LeftResponsiveOverlayNoAppBar.class, false, false, true, false),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to use more space the menu can be toggle manually toggled between a smaller and a larger state.
     * The menu position is on the left beneath the app-bar.
     * The initial state is always the larger menu.
     */
    LEFT_RESPONSIVE_SMALL(LeftResponsiveSmall.class, false, true, false, true),
    /**
     * An instance of the following {@link Behaviour} can only be collapsed manually with the burger button if a certain browser width is surpassed (f.e. most mobile devices) otherwise the menu will always visible.
     * To allow content to use more space the menu can be toggle manually toggled between a smaller and a larger state.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} is positioned on the left. If the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} is visible it will be shown beside on the right of the {@link com.github.appreciated.applayout.webcomponents.appmenu.AppMenu}.
     * The {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} will be hidden unless a certain device width is surpassed to allow to show the button to show the menu.
     */
    LEFT_RESPONSIVE_SMALL_NO_APP_BAR(LeftResponsiveSmallNoAppBar.class, false, false, false, true),
    /**
     * An instance of the following {@link Behaviour} can will not be collaped if shown on smaller devices. The menu elements will be shown inside the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} beside other elements added to it. This should
     * only be used if you only a few enu entries.
     */
    TOP(Top.class, true, true, false, false),

    /**
     * An instance of the following {@link Behaviour} can will not be collaped if shown on smaller devices. The menu elements will be shown inside the {@link com.github.appreciated.applayout.webcomponents.applayout.AppToolbar} below other elements added to it. This should
     * only be used if you only a few enu entries.
     */
    TOP_LARGE(TopLarge.class, true, true, false, false);

    private Class<? extends AppLayout> className;

    private boolean hasAppBar;
    private boolean overlay;
    private boolean small;
    private boolean top;

    Behaviour(Class<? extends AppLayout> className, boolean top, boolean hasAppBar, boolean overlay, boolean small) {
        this.className = className;
        this.top = top;
        this.hasAppBar = hasAppBar;
        this.overlay = overlay;
        this.small = small;
    }

    public AppLayout getInstance() {
        try {
            return className.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasAppBar() {
        return hasAppBar;
    }

    public boolean isOverlay() {
        return overlay;
    }

    public boolean isSmall() {
        return small;
    }

    public boolean isTop() {
        return top;
    }
}
