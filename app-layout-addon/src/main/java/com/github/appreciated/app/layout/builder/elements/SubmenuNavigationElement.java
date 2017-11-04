package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.builder.Provider;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import java.util.List;

public class SubmenuNavigationElement extends AbstractNavigationElement<Component, SubmenuNavigationElement> {

    private final String title;
    private final Resource icon;
    private List<AbstractNavigationElement> submenuElements;
    private Provider<String, String> captionInterceptor;

    public SubmenuNavigationElement(String title, Resource icon, List<AbstractNavigationElement> submenuElements) {
        this.title = title;
        this.icon = icon;
        this.submenuElements = submenuElements;
    }

    public List<AbstractNavigationElement> getSubmenuElements() {
        return submenuElements;
    }

    @Override
    SubmenuNavigationElement getInfo() {
        return this;
    }

    public Resource getIcon() {
        return icon;
    }

    public String getTitle() {
        if (captionInterceptor == null) {
            return title;
        } else {
            return captionInterceptor.get(title);
        }
    }

    @Override
    public void setProvider(AppLayout provider) {
        setProvider(provider.getDrawerSubmenuElementProvider());
    }

    @Override
    public void setProvider(AppLayout provider, AppLayout.Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerSubmenuElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopSubmenuElementProvider());
                break;
        }
    }

    public boolean requiresNavigator() {
        for (AbstractNavigationElement submenuElement : submenuElements) {
            if (submenuElement instanceof NavigatorNavigationElement) {
                return true;
            } else if (submenuElement instanceof SubmenuNavigationElement && ((SubmenuNavigationElement) submenuElement).requiresNavigator()) {
                return true;
            }
        }
        return false;
    }

    public void setCaptionInterceptor(Provider<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
