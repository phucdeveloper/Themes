package com.philipstudio.themes.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Category {
    private String name;
    private String avatar;
    private String link;

    public Category(){}

    public Category(String name, String avatar, String link) {
        this.name = name;
        this.avatar = avatar;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLink() {
        return link;
    }
}
