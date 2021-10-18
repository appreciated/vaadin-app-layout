package com.github.appreciated.app.layout;

import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

@Theme(themeClass = Lumo.class, variant = Material.DARK)
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Push
public class AppShellConfigurator implements com.vaadin.flow.component.page.AppShellConfigurator {
}
