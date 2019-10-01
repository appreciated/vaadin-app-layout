package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.polymertemplate.Id;

public interface LeftLayouts {


    @Tag(Left.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left.js")
    class Left extends AbstractLeftAppLayoutBase {

        public final static String TAG = "app-layout-left";

        @Override
        public String getStyleName() {
            return "left";
        }
    }

    @Tag(LeftHybrid.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-hybrid.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-hybrid.css")
    class LeftHybrid extends AbstractLeftAppLayoutBase {

        public final static String TAG = "app-layout-left-hybrid";

        @Override
        public String getStyleName() {
            return "left-hybrid";
        }
    }

    class LeftHybridSmall extends LeftHybrid {

        public final static String TAG = LeftHybrid.TAG;

        @Id("drawerLayout")
        AppDrawerLayout layout;

        public LeftHybridSmall() {
            layout.getElement().getClassList().add("small");
            this.getElement().getClassList().add("small");
        }

    }

    @Tag(LeftOverlay.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-overlay.js")
    class LeftOverlay extends AbstractLeftAppLayoutBase {
        public final static String TAG = "app-layout-left-overlay";

        @Override
        public String getStyleName() {
            return "left-overlay";
        }
    }

    abstract class AbstractLeftResponsive extends AbstractLeftAppLayoutBase {

        /**
         * Sets the responsive width.
         *
         * @param width width in pixels
         */
        public void setResponsiveWidth(String width) {
            getElement().setProperty("responsiveWidth", width);
        }

    }

    @Tag(LeftResponsive.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive.js")
    class LeftResponsive extends AbstractLeftResponsive {

        public final static String TAG = "app-layout-left-responsive";

        @Override
        public String getStyleName() {
            return "left-responsive";
        }
    }

    @Tag(LeftResponsiveDouble.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-double.js")
    class LeftResponsiveDouble extends AbstractLeftResponsive {
        public final static String TAG = "app-layout-left-responsive-double";

        @Override
        public String getStyleName() {
            return "left-responsive-double";
        }
    }

    abstract class AbstractLeftResponsiveHybrid extends AbstractLeftAppLayoutBase {

        /**
         * Sets the responsive width for narrow-mode.
         *
         * @param width width in pixels
         */
        public void setResponsiveWidthNarrow(String width) {
            getElement().setProperty("responsiveWidthNarrow", width);
        }

        /**
         * Sets the responsive width for wide-mode.
         *
         * @param width width in pixels
         */
        public void setResponsiveWidthWide(String width) {
            getElement().setProperty("responsiveWidthWide", width);
        }

    }

    @Tag(LeftResponsiveHybrid.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid.css")
    class LeftResponsiveHybrid extends AbstractLeftResponsiveHybrid {

        public final static String TAG = "app-layout-left-responsive-hybrid";

        @Override
        public String getStyleName() {
            return "left-responsive-hybrid";
        }
    }

    @Tag(LeftResponsiveHybridNoAppBar.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.css")
    class LeftResponsiveHybridNoAppBar extends AbstractLeftResponsiveHybrid {


        public final static String TAG = "app-layout-left-responsive-hybrid-no-app-bar";

        @Override
        public String getStyleName() {
            return "left-responsive-hybrid-no-app-bar";
        }
    }

    @Tag(LeftResponsiveHybridOverlayNoAppBar.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.css")
    class LeftResponsiveHybridOverlayNoAppBar extends AbstractLeftResponsiveHybrid {

        public final static String TAG = "app-layout-left-responsive-hybrid-overlay-no-app-bar";

        @Override
        public String getStyleName() {
            return "left-responsive-hybrid-overlay-no-app-bar";
        }
    }

    @Tag(LeftResponsiveOverlay.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-overlay.js")
    class LeftResponsiveOverlay extends AbstractLeftResponsive {


        public final static String TAG = "app-layout-left-responsive-overlay";

        @Override
        public String getStyleName() {
            return "left-responsive-overlay";
        }
    }

    @Tag(LeftResponsiveOverlayNoAppBar.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-overlay-no-app-bar.js")
    class LeftResponsiveOverlayNoAppBar extends AbstractLeftResponsive {


        public final static String TAG = "app-layout-left-responsive-overlay-no-app-bar";

        @Override
        public String getStyleName() {
            return "left-responsive-overlay-no-app-bar";
        }
    }

    @Tag(LeftResponsiveSmall.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-small.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-small.css")
    class LeftResponsiveSmall extends AbstractLeftResponsive {


        public final static String TAG = "app-layout-left-responsive-small";

        @Override
        public String getStyleName() {
            return "left-responsive-small";
        }
    }

    @Tag(LeftResponsiveSmallNoAppBar.TAG)
    @JsModule("./com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.js")
    @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.css")
    class LeftResponsiveSmallNoAppBar extends AbstractLeftResponsive {

        public final static String TAG = "app-layout-left-responsive-small-no-app-bar";

        @Override
        public String getStyleName() {
            return "left-responsive-small-no-app-bar";
        }
    }
}
