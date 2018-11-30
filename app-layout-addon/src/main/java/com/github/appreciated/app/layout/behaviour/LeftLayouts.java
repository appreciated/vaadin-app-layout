package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;

public interface LeftLayouts {

  @Tag("app-layout-left")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left.html")
  class Left extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left";
      }
  }

  @Tag("app-layout-left-hybrid")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-hybrid.html")
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
      }

  }

  @Tag("app-layout-left-overlay")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-overlay.html")
  class LeftOverlay extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-overlay";
      }
  }

  @Tag("app-layout-left-responsive")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive.html")
  class LeftResponsive extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive";
      }
  }

  @Tag("app-layout-left-responsive-hybrid")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-hybrid.html")
  class LeftResponsiveHybrid extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-no-app-bar")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.html")
  class LeftResponsiveHybridNoAppBar extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-hybrid-overlay-no-app-bar")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-hybrid-overlay-no-app-bar.html")
  class LeftResponsiveHybridOverlayNoAppBar extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-hybrid-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-overlay")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-overlay.html")
  class LeftResponsiveOverlay extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay";
      }
  }

  @Tag("app-layout-left-responsive-overlay-no-app-bar")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-overlay-no-app-bar.html")
  class LeftResponsiveOverlayNoAppBar extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-overlay-no-app-bar";
      }
  }

  @Tag("app-layout-left-responsive-small")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-small.html")
  class LeftResponsiveSmall extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-small";
      }
  }

  @Tag("app-layout-left-responsive-small-no-app-bar")
  @HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-small-no-app-bar.html")
  class LeftResponsiveSmallNoAppBar extends AbstractLeftAppLayoutBase {

      @Override
      public String getStyleName() {
          return "left-responsive-small-no-app-bar";
      }
  }
}
