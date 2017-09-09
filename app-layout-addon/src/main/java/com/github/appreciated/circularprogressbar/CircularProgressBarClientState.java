package com.github.appreciated.circularprogressbar;

/**
 * Created by Johannes on 11.01.2017.
 */

import com.vaadin.shared.ui.JavaScriptComponentState;

public class CircularProgressBarClientState extends JavaScriptComponentState {
    public float value;
    public float scale = 1.0f;
    public String label = null;
}