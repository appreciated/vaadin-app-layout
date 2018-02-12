package com.github.appreciated.demo.vaadin.elements;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractElementsDemo extends CustomComponent {

    private final VerticalLayout layout = new VerticalLayout();

    public AbstractElementsDemo() {
        layout.setMargin(new MarginInfo(false, false, false, true));
        layout.setSpacing(true);
        setCompositionRoot(layout);
    }

    @Override
    public void attach() {
        super.attach();
        if (layout.getComponentCount() == 0) {

            Label descriptionLabel = new Label(getDemoDescription());
            descriptionLabel.setWidth("600px");
            layout.addComponent(descriptionLabel);
            layout.addComponent(getDemoView());
        }
    }

    protected abstract String getDemoDescription();

    protected abstract Component getDemoView();
}