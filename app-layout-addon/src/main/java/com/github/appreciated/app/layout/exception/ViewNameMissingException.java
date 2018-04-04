package com.github.appreciated.app.layout.exception;

import com.vaadin.navigator.View;

public class ViewNameMissingException extends RuntimeException {
    public ViewNameMissingException(Class<? extends View> view) {
        super("The class " + view.getName() + " seems to miss the required view name to add it to the Navigator. " +
                "To add it either add the Annotation '@NavigatorViewName' " +
                "or by calling the method '<AppLayoutBuilder>::add()' properly!");
    }
}
