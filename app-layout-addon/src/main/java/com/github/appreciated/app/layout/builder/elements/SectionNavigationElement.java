package com.github.appreciated.app.layout.builder.elements;

public class SectionNavigationElement extends AbstractNavigationElement<SectionNavigationElement> {

    private String name;

    public SectionNavigationElement(String name) {
        this.name = name;
    }

    @Override
    SectionNavigationElement getInfo() {
        return this;
    }

    public String getName() {
        return name;
    }
}
