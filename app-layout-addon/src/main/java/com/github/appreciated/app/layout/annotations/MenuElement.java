package com.github.appreciated.app.layout.annotations;


import com.vaadin.icons.VaadinIcons;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface MenuElement {
    String value();
    VaadinIcons icon();
}
