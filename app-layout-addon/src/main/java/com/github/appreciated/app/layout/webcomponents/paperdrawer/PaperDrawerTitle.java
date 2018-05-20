package com.github.appreciated.app.layout.webcomponents.paperdrawer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("paper-drawer-title")
@HtmlImport("bower_components/paper-drawer/paper-drawer-title.html")
public class PaperDrawerTitle extends Component {

    public PaperDrawerTitle(String title, String subtitle, String image) {
        if (title != null) {
            setTitle(title);
        }
        if (subtitle != null) {
            setSubtitle(subtitle);
        }
        if (image != null) {
            setImageSrc(image);
        }
    }

    public String getSubtitle() {
        return getElement().getAttribute("email");
    }

    public void setSubtitle(String subtitle) {
        getElement().setAttribute("email", subtitle);
    }

    public String getTitle() {
        return getElement().getAttribute("name");
    }

    public void setTitle(String title) {
        getElement().setAttribute("name", title);
    }

    public void setImageSrc(String src) {
        getElement().setAttribute("photo", src);
    }

    public String getPhoto() {
        return getElement().getAttribute("photo");
    }
}