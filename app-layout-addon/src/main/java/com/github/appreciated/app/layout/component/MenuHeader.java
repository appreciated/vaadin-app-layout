package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerTitle;

/**
 * A simple container component which may contain an image and two labels concerned component won't be added to its parent
 */
public class MenuHeader extends PaperDrawerTitle {

    public MenuHeader(String title, String subtitle, String src) {
        super(title, subtitle, src);
        getElement().getStyle().set("width", "100%");
    }

}