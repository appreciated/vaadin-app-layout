package com.github.appreciated.app.layout.exception;

import com.vaadin.navigator.View;

public class ViewNameMissingException extends RuntimeException {
    public ViewNameMissingException(Class<? extends View> view) {
        super("The class " + view.getName() + " seems to miss the required view name to add it to the Navigator. " +
                "To fix this issue either add the Annotation '@NavigatorViewName' to the View " + view.getName() +
                "or add the View with a non null value '<AppLayoutBuilder>::add()' to the AppLayout!");
    }
}
