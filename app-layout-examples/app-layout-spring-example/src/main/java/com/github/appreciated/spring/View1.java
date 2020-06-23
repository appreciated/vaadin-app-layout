package com.github.appreciated.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainAppLayout.class)
public class View1 extends VerticalLayout {

    public View1(@Autowired MessageBean bean, @Autowired MainAppLayout appLayout) {

        Button button = new Button("Navigate to View2",
                e -> this.getUI().ifPresent(ui -> ui.navigate(View2.class)));
        add(button);

        // can access the AppLayout instance via dependency injection
        int notificationCount = appLayout.getNotifications().getNotificationSize();
        add(new Paragraph("You have " + notificationCount + " notification(s)"));

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
