package com.philipstudio.themes.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String idUser;
    private String email;
    private String password;
    private String nickname;
    private String avatar;

    public User(){}

    public User(String idUser, String email, String password, String nickname, String avatar) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }
}
