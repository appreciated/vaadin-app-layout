package com.github.appreciated.circularprogressbar;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * Created by Johannes on 10.01.2017.
 */
@JavaScript({"vaadin://addons/circular-progressbar/circular-progressbar_connector.js"})
public class CircularProgressBarClient extends AbstractJavaScriptComponent {
    public CircularProgressBarClient() {
        if (getHeight() == -1.0F && getWidth() == -1.0F) {
            setWidth(125, Unit.PIXELS);
            setHeight(125, Unit.PIXELS);
        }
        setPrimaryStyleName("circular-progressbar");
    }

    public void setValue(float value) {
        getState().value = value;
    }

    public float getProgress() {
        return getState().value;
    }

    public void setScale(float value) {
        getState().scale = value;
    }

    public float getScale() {
        return getState().scale;
    }

    public void setLabel(String value) {
        getState().label = value;
    }

    public String getLabel() {
        return getState().label;
    }

    @Override
    protected CircularProgressBarClientState getState() {
        return (CircularProgressBarClientState) super.getState();
    }

}
