package com.github.appreciated.app.layout.builder.elements;

import com.vaadin.server.Resource;

import java.util.List;

public class SubmenuNavigationElement extends AbstractNavigationElement<SubmenuNavigationElement> {

    private final String title;
    private final Resource icon;
    private List<AbstractNavigationElement> submenuElements;

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
        return title;
    }
}
