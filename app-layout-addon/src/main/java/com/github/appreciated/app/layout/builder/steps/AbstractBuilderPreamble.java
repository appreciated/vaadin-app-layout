package com.github.appreciated.app.layout.builder.steps;

import com.github.appreciated.app.layout.builder.AbstractAppLayoutBuilderBase;

public class AbstractBuilderPreamble<T extends AbstractAppLayoutBuilderBase> {

    private T builder;

    public AbstractBuilderPreamble(T builder) {
        this.builder = builder;
    }

    protected T getBuilder() {
        return builder;
    }

}
