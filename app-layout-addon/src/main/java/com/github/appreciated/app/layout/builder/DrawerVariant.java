package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.drawer.*;

public enum DrawerVariant {
    LEFT(LeftNavigationDrawer.class, true, false, false),
    LEFT_RESPONSIVE(LeftNavigationDrawerResponsive.class, true, false, false),
    LEFT_OVERLAY(LeftNavigationDrawerOverlay.class, true, true, false),
    LEFT_RESPONSIVE_OVERLAY(LeftNavigationDrawerResponsiveOverlay.class, true, true, false),
    LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR(LeftNavigationDrawerResponsiveOverlayNoAppBar.class, false, true, false),
    LEFT_RESPONSIVE_SMALL(LeftNavigationDrawerResponsiveSmall.class, true, false, true),
    LEFT_RESPONSIVE_SMALL_NO_APP_BAR(LeftNavigationDrawerResponsiveSmallNoAppBar.class, false, false, true);

    private Class<? extends AbstractNavigationDrawer> aClass;
    private boolean hasAppBar;
    private boolean overlay;
    private boolean small;

    DrawerVariant(Class<? extends AbstractNavigationDrawer> aClass, boolean hasAppBar, boolean overlay, boolean small) {
        this.aClass = aClass;
        this.hasAppBar = hasAppBar;
        this.overlay = overlay;
        this.small = small;
    }

    public AbstractNavigationDrawer getInstance() {
        try {
            return aClass.newInstance();
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
}
