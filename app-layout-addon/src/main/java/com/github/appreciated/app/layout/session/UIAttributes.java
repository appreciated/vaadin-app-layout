package com.github.appreciated.app.layout.session;

import com.vaadin.flow.component.UI;

import java.util.HashMap;
import java.util.Map;

public class UIAttributes {

    Map<UI, Map<Class, Object>> map = new HashMap<>();

    private UIAttributes() {
    }

    public static <T> T get(Class<T> type) {
        UIAttributes session = getSession();
        UI ui = UI.getCurrent();
        if (!session.map.containsKey(UI.getCurrent())) {
            session.map.put(ui, new HashMap<>());
        }
        return (T) session.map.get(ui).get(type);
    }

    public static <T> void set(Class<T> type, T value) {
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
