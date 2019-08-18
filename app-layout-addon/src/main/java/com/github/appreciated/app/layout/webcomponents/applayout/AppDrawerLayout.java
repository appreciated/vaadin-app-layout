package com.github.appreciated.app.layout.webcomponents.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("app-drawer-layout")
@NpmPackage(value = "@polymer/app-layout", version = "3.1.0")
@JsModule("@polymer/app-layout/app-drawer-layout/app-drawer-layout.js")
public class AppDrawerLayout extends Component implements HasComponents {

}
