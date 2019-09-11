package com.github.appreciated.app.layout.addons.search.overlay;

import com.vaadin.flow.component.ClickNotifier;

public class QueryPair<T> {
    T query;
    ClickNotifier notifier;

    public QueryPair(T query, ClickNotifier notifier) {
        this.query = query;
        this.notifier = notifier;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public ClickNotifier getNotifier() {
        return notifier;
    }

    public void setNotifier(ClickNotifier notifier) {
        this.notifier = notifier;
    }
}
