package com.github.appreciated.app.layout.builder.entities;

import com.vaadin.server.Resource;

public class DefaultNotification {
    String title;
    String description;
    Resource image;

    public DefaultNotification(String title, String description) {
        this(title, description, null);
    }

    public DefaultNotification(String title, String description, Resource image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }
}
