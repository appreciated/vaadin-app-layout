package com.github.appreciated.app.layout.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface MenuElement {
    String name() default "";
}
