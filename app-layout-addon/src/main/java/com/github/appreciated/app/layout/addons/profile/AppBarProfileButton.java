package com.github.appreciated.app.layout.addons.profile;

import com.github.appreciated.app.layout.component.appbar.ContextMenuAppBarButton;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;

public class AppBarProfileButton extends ContextMenuAppBarButton {

    public AppBarProfileButton() {
        super(VaadinIcon.USER.create());
        getWrappedComponent().addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
    }

    public AppBarProfileButton(Image icon) {
        super(icon);
    }

}
