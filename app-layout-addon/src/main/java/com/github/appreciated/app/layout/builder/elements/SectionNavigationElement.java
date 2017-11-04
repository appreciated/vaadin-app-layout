package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayout;
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

    @Override
    public void setProvider(AppLayout provider) {
        setProvider(provider.getDrawerSectionElementProvider());
    }

    @Override
    public void setProvider(AppLayout provider, AppLayout.Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerSectionElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopSectionElementProvider());
                break;
        }
    }

    public void setCaptionInterceptor(Provider<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
