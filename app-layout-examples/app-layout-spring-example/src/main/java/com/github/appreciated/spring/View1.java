package com.github.appreciated.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainAppLayout.class)
public class View1 extends VerticalLayout {

    public View1(@Autowired MessageBean bean) {

    	Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
    }

    Paragraph getLabel() {
        Paragraph label = new Paragraph("........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ");
        label.setWidth("100%");
        return label;
    }

}