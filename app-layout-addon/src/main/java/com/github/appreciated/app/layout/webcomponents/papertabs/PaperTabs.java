package com.github.appreciated.app.layout.webcomponents.papertabs;


import com.github.appreciated.app.layout.helper.LayoutHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("paper-tabs")
@NpmPackage(value = "@polymer/app-layout", version = "3.1.0")
@JsModule("@polymer/paper-tabs/paper-tabs.js")
public class PaperTabs extends Component implements HasComponents, HasSize {

    public void setScrollable(boolean scrollable) {
        getElement().setAttribute("scrollable", scrollable);
    }

    public void setFitContainer(boolean fit) {
        getElement().setAttribute("fit-container", fit);
    }

    public void setSelected(PaperTab tab) {
        final int indexOfTab = getElement().indexOfChild(tab.getElement());
        long index = getChildren()
                .filter(component -> getElement().indexOfChild(component.getElement()) < indexOfTab)
                .filter(component -> component instanceof PaperTab)
                .count();
        setSelected((int) index);
    }

    public void setSelected(int i) {
        getElement().setAttribute("selected", String.valueOf(i));
    }

    @Override
    public void add(Component... components) {
        for (Component component : components) {
            if (component instanceof PaperTab) {
                ((PaperTab) component).setParent(this);
            }
        }
        LayoutHelper.add(this, components);
    }
}
