package com.github.appreciated.builder;

import com.github.appreciated.layout.drawer.*;

public enum DrawerVariant {
    LEFT(LeftNavigationDrawer.class),
    LEFT_RESPONSIVE(LeftNavigationDrawerResponsive.class),
    LEFT_OVERLAY(LeftNavigationDrawerOverlay.class),
    LEFT_RESPONSIVE_OVERLAY(LeftNavigationDrawerResponsiveOverlay.class),
    LEFT_RESPONSIVE_OVERLAY_NOAPPBAR(LeftNavigationDrawerResponsiveOverlayNoAppBar.class);

    private Class<? extends AbstractNavigationDrawer> aClass;

    DrawerVariant(Class<? extends AbstractNavigationDrawer> aClass) {
        this.aClass = aClass;
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

}
