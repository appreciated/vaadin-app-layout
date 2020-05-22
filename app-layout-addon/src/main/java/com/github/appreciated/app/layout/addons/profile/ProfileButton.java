package com.github.appreciated.app.layout.addons.profile;

import com.github.appreciated.app.layout.component.appbar.ContextMenuAppBarButton;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ProfileButton extends ContextMenuAppBarButton {
    private static final long serialVersionUID = 1L;

    public ProfileButton() {
        super(VaadinIcon.USER.create());
    }

    public ProfileButton(Image icon) {
        super(icon);
    }

}
