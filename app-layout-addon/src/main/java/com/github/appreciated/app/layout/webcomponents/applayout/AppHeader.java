package com.github.appreciated.app.layout.webcomponents.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("app-header")
@NpmPackage(value = "@polymer/app-layout", version = "3.1.0")
@JsModule("@polymer/app-layout/app-header/app-header.js")
public class AppHeader extends Component implements HasSize, HasComponents {
    private static final long serialVersionUID = 1L;

}


