package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ProfileButton extends ContextMenuAppBarButton {

    public ProfileButton(String image, String placeholder) {
        super(new Image(image, placeholder));
    }

    public ProfileButton(VaadinIcon icon) {
        super(new Icon(icon));
        ((Icon) getButtonContent()).setSize("100%");
    }
}
