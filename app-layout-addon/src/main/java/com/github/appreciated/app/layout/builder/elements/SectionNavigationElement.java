package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.vaadin.flow.component.HasElement;


/**
 * A wrapper class for a menu element that is a {@link SectionNavigationElement}.
 */
public class SectionNavigationElement extends AbstractNavigationElement<HasElement, SectionNavigationElement> implements HasCaptionInterceptor {

    private String name;
    private Factory<String, String> captionInterceptor;

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

    @Override
    public void setProvider(AppLayoutElementBase provider) {
        setProvider(provider.getSectionElementProvider());
    }


    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
