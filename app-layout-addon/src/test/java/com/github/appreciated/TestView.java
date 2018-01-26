package com.github.appreciated;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;

@MenuCaption("test")
@MenuIcon(VaadinIcons.ABACUS)
@NavigatorViewName("test")
public class TestView extends CssLayout implements View {
}
