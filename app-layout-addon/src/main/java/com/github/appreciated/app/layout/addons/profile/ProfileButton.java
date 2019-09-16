package com.github.appreciated.app.layout.addons.profile;

import com.github.appreciated.app.layout.component.appbar.ContextMenuAppBarButton;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ProfileButton extends ContextMenuAppBarButton {

    public ProfileButton() {
        super(VaadinIcon.USER.create());
    }

    public ProfileButton(Image icon) {
        super(icon);
    }

}
