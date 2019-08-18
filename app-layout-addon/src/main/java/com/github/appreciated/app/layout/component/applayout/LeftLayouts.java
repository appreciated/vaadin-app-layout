package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.polymertemplate.Id;

public interface LeftLayouts {

  @Tag("app-layout-left")
  @JsModule("./com/github/appreciated/app-layout/left/left.js")
  class Left extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left";
      }
  }

  @Tag("app-layout-left-hybrid")
  @JsModule("./com/github/appreciated/app-layout/left/left-hybrid.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-hybrid.css")
  class LeftHybrid extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-hybrid";
      }
  }

  class LeftHybridSmall extends LeftHybrid {

      @Id("drawerLayout")
      AppDrawerLayout layout;

      public LeftHybridSmall() {
          layout.getElement().getClassList().add("small");
          this.getElement().getClassList().add("small");
      }

  }

  @Tag("app-layout-left-overlay")
  @JsModule("./com/github/appreciated/app-layout/left/left-overlay.js")
  class LeftOverlay extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-overlay";
      }
  }

  abstract class AbstractLeftResponsive extends AbstractLeftAppLayoutBase {

      /** Sets the responsive width. */
      public void setResponsiveWidth(String width) {
          getElement().setProperty("responsiveWidth", width);
      }

  }

  @Tag("app-layout-left-responsive")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive.js")
  class LeftResponsive extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive";
      }
  }

  @Tag("app-layout-left-responsive-double")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-double.js")
  class LeftResponsiveDouble extends AbstractLeftResponsive {
      @Override
      public String getStyleName() {
            return "left-responsive-double";
        }
  }

  abstract class AbstractLeftResponsiveHybrid extends AbstractLeftAppLayoutBase {

      /** Sets the responsive width for narrow-mode. */
      public void setResponsiveWidthNarrow(String width) {
          getElement().setProperty("responsiveWidthNarrow", width);
      }

      /** Sets the responsive width for wide-mode. */
      public void setResponsiveWidthWide(String width) {
          getElement().setProperty("responsiveWidthWide", width);
      }

  }

  @Tag("app-layout-left-responsive-hybrid")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid.css")
  class LeftResponsiveHybrid extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-no-app-bar")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.css")
  class LeftResponsiveHybridNoAppBar extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-overlay-no-app-bar")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.css")
  class LeftResponsiveHybridOverlayNoAppBar extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-overlay")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-overlay.js")
  class LeftResponsiveOverlay extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay";
      }
  }

  @Tag("app-layout-left-responsive-overlay-no-app-bar")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-overlay-no-app-bar.js")
  class LeftResponsiveOverlayNoAppBar extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-small")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-small.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-small.css")
  class LeftResponsiveSmall extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-small";
      }
  }

  @Tag("app-layout-left-responsive-small-no-app-bar")
  @JsModule("./com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.js")
  @StyleSheet("./com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.css")
  class LeftResponsiveSmallNoAppBar extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-small-no-app-bar";
      }
  }
}
