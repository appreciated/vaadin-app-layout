package com.github.appreciated.app.layout.session;

import com.vaadin.flow.component.UI;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UIAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<UI, Map<Class, Object>> map = new HashMap<>();

    private UIAttributes() {
    }

    /**
     * Needs to be called from the UI Thread.
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T get(Class<T> type) {
        UIAttributes session = getSession();
        UI ui = UI.getCurrent();
        if (!session.map.containsKey(UI.getCurrent())) {
            session.map.put(ui, new HashMap<>());
        }
        return (T) session.map.get(ui).get(type);
    }

    /**
     *  Needs to be called from the UI Thread.
     * @param type
     * @param value
     * @param <T>
     */
    public static <T extends Serializable> void set(Class<T> type, T value) {
        UIAttributes session = getSession();
        UI ui = UI.getCurrent();
        if (!session.map.containsKey(UI.getCurrent())) {
            session.map.put(ui, new HashMap<>());
        }
        session.map.get(ui).put(type, value);
    }

    private static UIAttributes getSession() {
        if (UI.getCurrent().getSession().getAttribute(UIAttributes.class) == null) {
            UI.getCurrent().getSession().setAttribute(UIAttributes.class, new UIAttributes());
        }
        return UI.getCurrent().getSession().getAttribute(UIAttributes.class);
    }
}
