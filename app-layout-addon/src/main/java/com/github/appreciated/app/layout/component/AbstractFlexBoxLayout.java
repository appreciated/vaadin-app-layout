package com.github.appreciated.app.layout.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class AbstractFlexBoxLayout extends CssLayout {

    public AbstractFlexBoxLayout(String primaryStyle) {
        addStyleName(primaryStyle);
    }

    public AbstractFlexBoxLayout(String primaryStyle, Component[] components) {
        addStyleName(primaryStyle);
        addComponents(components);
    }

    public void grow(Component child) {
        addStyleIfIsChild(child, "grow");
    }

    public void shrink(Component child) {
        addStyleIfIsChild(child, "shrink");
    }

    public void growAndShrink(Component child) {
        addStyleIfIsChild(child, "grow", "shrink");
    }

    public void setOverflowAuto(boolean overflow) {
        if (overflow) {
            addStyleName("overflow-auto");
        } else {
            removeStyleName("overflow-auto");
        }
    }

    private void addStyleIfIsChild(Component component, String... style) {
        if (this.components.contains(component)) {
            component.addStyleNames(style);
        }
    }
}
