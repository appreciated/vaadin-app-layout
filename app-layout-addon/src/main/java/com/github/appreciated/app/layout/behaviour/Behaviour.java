package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.behaviour.impl.*;

public enum Behaviour {
    LEFT(Left.class, true, false, false),
    LEFT_RESPONSIVE(LeftResponsive.class, true, false, false),
    LEFT_HYBRID(LeftHybrid.class, true, false, false),
    LEFT_RESPONSIVE_HYBRID(LeftResponsiveHybrid.class, true, false, false),
    LEFT_RESPONSIVE_HYBRID_NO_APP_BAR(LeftResponsiveHybridNoAppBar.class, true, false, false),
    LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR(LeftResponsiveHybridOverlayNoAppBar.class, true, false, false),
    LEFT_OVERLAY(LeftOverlay.class, true, true, false),
    LEFT_RESPONSIVE_OVERLAY(LeftResponsiveOverlay.class, true, true, false),
    LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR(LeftResponsiveOverlayNoAppBar.class, false, true, false),
    LEFT_RESPONSIVE_SMALL(LeftResponsiveSmall.class, true, false, true),
    LEFT_RESPONSIVE_SMALL_NO_APP_BAR(LeftResponsiveSmallNoAppBar.class, false, false, true);

    private Class<? extends AbstractAppLayoutBehaviour> className;
    private boolean hasAppBar;
    private boolean overlay;
    private boolean small;

    Behaviour(Class<? extends AbstractAppLayoutBehaviour> className, boolean hasAppBar, boolean overlay, boolean small) {
        this.className = className;
        this.hasAppBar = hasAppBar;
        this.overlay = overlay;
        this.small = small;
    }

    public AbstractAppLayoutBehaviour getInstance() {
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
}
