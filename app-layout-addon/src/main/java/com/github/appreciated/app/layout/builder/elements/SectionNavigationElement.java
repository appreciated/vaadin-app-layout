package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.Provider;
import com.vaadin.ui.Component;

public class SectionNavigationElement extends AbstractNavigationElement<Component, SectionNavigationElement> {

    private String name;
    private Provider<String, String> captionInterceptor;

    public SectionNavigationElement(String name) {
        this.name = name;
    }

    @Override
    SectionNavigationElement getInfo() {
        return this;
    }

    public String getName() {
        if (captionInterceptor == null) {
            return name;
        } else {
            return captionInterceptor.get(name);
        }
    }

    public void setCaptionInterceptor(Provider<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
