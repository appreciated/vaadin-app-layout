package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.vaadin.ui.Component;

/**
 * A wrapper class for a MenuElement that is simply meant to display a Section.
 */
public class SectionNavigationElement extends AbstractNavigationElement<Component, SectionNavigationElement> implements HasCaptionInterceptor {

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
    public void setProvider(AppLayoutComponent provider) {
        setProvider(provider.getDrawerSectionElementProvider());
    }

    @Override
    public void setProvider(AppLayoutComponent provider, Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerSectionElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopSectionElementProvider());
                break;
        }
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
