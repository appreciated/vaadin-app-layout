package com.github.appreciated.app.layout.addons.profile;

import com.github.appreciated.app.layout.component.appbar.ContextMenuAppBarButton;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ProfileButton extends ContextMenuAppBarButton {

    public ProfileButton() {
        super(VaadinIcon.USER.create());
        getWrappedComponent().addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
    }

    public ProfileButton(Image icon) {
        super(icon);
    }

}
