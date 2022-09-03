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
        return (T) getSession().getCurrentUIData().get(type);
    }

    /**
     *  Needs to be called from the UI Thread.
     * @param type
     * @param value
     * @param <T>
     */
    public static <T extends Serializable> void set(Class<T> type, T value) {
    	getSession().getCurrentUIData().put(type, value);
    }

    private Map<Class, Object> getCurrentUIData() {
        UI ui = UI.getCurrent();
        if (!map.containsKey(ui)) {
            map.put(ui, new HashMap<>());
            ui.addDetachListener(event -> {
                map.remove(ui);
            });
        }
        return map.get(ui);
    }

    private static UIAttributes getSession() {
        if (UI.getCurrent().getSession().getAttribute(UIAttributes.class) == null) {
            UI.getCurrent().getSession().setAttribute(UIAttributes.class, new UIAttributes());
        }
        return UI.getCurrent().getSession().getAttribute(UIAttributes.class);
    }
}
