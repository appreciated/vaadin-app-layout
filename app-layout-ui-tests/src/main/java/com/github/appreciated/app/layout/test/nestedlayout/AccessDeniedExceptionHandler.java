package com.github.appreciated.app.layout.test.nestedlayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Intellij IDEA.
 * Date: 27. 2. 2019 12:53
 *
 * @author Gabriel Gecy
 */
public class AccessDeniedExceptionHandler extends Div implements HasErrorParameter<AccessDeniedException> {

    public static final String ID = "exception-handler";
    public static final String ERROR_MESSAGE = "Tried to navigate to a view without correct access rights";

    public AccessDeniedExceptionHandler() {
        setId(ID);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<AccessDeniedException> parameter) {
        getElement().setText(ERROR_MESSAGE);
        return HttpServletResponse.SC_FORBIDDEN;
    }
}
