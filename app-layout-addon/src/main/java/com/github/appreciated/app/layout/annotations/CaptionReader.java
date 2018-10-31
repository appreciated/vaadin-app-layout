package com.github.appreciated.app.layout.annotations;

import com.vaadin.flow.component.Component;

public class CaptionReader {

    public static Caption read(Class<? extends Component> className) {
        return className.getAnnotation(Caption.class);
    }
}
