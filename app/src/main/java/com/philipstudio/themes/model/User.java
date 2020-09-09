package com.philipstudio.themes.model;

public class User {
    private String idUser;
    private String nickname;
    private String avatar;

    public User(String idUser, String nickname, String avatar) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }
}
