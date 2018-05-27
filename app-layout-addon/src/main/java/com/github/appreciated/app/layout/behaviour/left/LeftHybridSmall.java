package com.github.appreciated.app.layout.behaviour.left;

import com.vaadin.flow.component.Tag;

import java.io.IOException;

@Tag("small")
public class LeftHybridSmall extends LeftHybrid {

    public LeftHybridSmall() throws IOException {
        getElement().getClassList().add("small");
    }

}
