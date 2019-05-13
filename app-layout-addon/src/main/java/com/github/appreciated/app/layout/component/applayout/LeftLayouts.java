package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.polymertemplate.Id;

public interface LeftLayouts {

  @Tag("app-layout-left")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left.html")
  class Left extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left";
      }
  }

  @Tag("app-layout-left-hybrid")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-hybrid.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-hybrid.css")
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
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-overlay.html")
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
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive.html")
  class LeftResponsive extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive";
      }
  }

  @Tag("app-layout-left-responsive-double")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-double.html")
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
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid.css")
  class LeftResponsiveHybrid extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-no-app-bar")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.css")
  class LeftResponsiveHybridNoAppBar extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-overlay-no-app-bar")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.css")
  class LeftResponsiveHybridOverlayNoAppBar extends AbstractLeftResponsiveHybrid {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-overlay")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-overlay.html")
  class LeftResponsiveOverlay extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay";
      }
  }

  @Tag("app-layout-left-responsive-overlay-no-app-bar")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-overlay-no-app-bar.html")
  class LeftResponsiveOverlayNoAppBar extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-small")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-small.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-responsive-small.css")
  class LeftResponsiveSmall extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-small";
      }
  }

  @Tag("app-layout-left-responsive-small-no-app-bar")
  @HtmlImport("frontend://src/com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.html")
  @StyleSheet("frontend://src/com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.css")
  class LeftResponsiveSmallNoAppBar extends AbstractLeftResponsive {

      @Override
      public String getStyleName() {
          return "left-responsive-small-no-app-bar";
      }
  }
}
