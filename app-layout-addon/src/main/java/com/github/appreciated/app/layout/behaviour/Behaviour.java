package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.behaviour.left.*;
import com.github.appreciated.app.layout.behaviour.top.Top;
import com.github.appreciated.app.layout.behaviour.top.TopLarge;

public enum Behaviour {
    LEFT(Left.class, false, true, false, false),
    LEFT_RESPONSIVE(LeftResponsive.class, false, true, false, false),
    LEFT_HYBRID(LeftHybrid.class, false, true, false, false),
    LEFT_RESPONSIVE_HYBRID(LeftResponsiveHybrid.class, false, true, false, false),
    LEFT_RESPONSIVE_HYBRID_NO_APP_BAR(LeftResponsiveHybridNoAppBar.class, false, false, false, false),
    LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR(LeftResponsiveHybridOverlayNoAppBar.class, false, false, false, false),
    LEFT_OVERLAY(LeftOverlay.class, false, true, true, false),
    LEFT_RESPONSIVE_OVERLAY(LeftResponsiveOverlay.class, false, true, true, false),
    LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR(LeftResponsiveOverlayNoAppBar.class, false, false, true, false),
    LEFT_RESPONSIVE_SMALL(LeftResponsiveSmall.class, false, true, false, true),
    LEFT_RESPONSIVE_SMALL_NO_APP_BAR(LeftResponsiveSmallNoAppBar.class, false, false, false, true),
    TOP(Top.class, true, true, false, false),
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
