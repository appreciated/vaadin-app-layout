package com.github.appreciated.app.layout.addons.profile;

import com.vaadin.flow.component.html.Image;

public class AppBarImageProfileButton extends AppBarProfileButton {

    public AppBarImageProfileButton(Image image) {
        super(image);
        init(image);
    }

    private void init(Image image) {
        getWrappedComponent().getElement().getStyle().set("line-height", "var(--app-layout-menu-button-height)");
        image.setWidth("calc(var(--app-layout-menu-button-height) - 4px)");
        image.setHeight("calc(var(--app-layout-menu-button-height) - 4px)");
        image.getStyle().set("border-radius", "100px");
    }

    public AppBarImageProfileButton(String image) {
        super();
        Image imageComponent = new Image(image, "Profile image");
        getWrappedComponent().setIcon(imageComponent);
        init(imageComponent);
    }

}
