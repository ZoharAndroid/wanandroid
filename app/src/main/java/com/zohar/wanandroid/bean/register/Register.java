package com.zohar.wanandroid.bean.register;

import java.util.List;

/**
 * Created by zohar on 2019/8/29 11:32
 * Describe:
 */
public class Register {
    /**
     *      *         "admin": false,
     *      *         "chapterTops": [],
     *      *         "collectIds": [],
     *      *         "email": "",
     *      *         "icon": "",
     *      *         "id": 29523,
     *      *         "nickname": "ZoharAndroid",
     *      *         "password": "",
     *      *         "token": "",
     *      *         "type": 0,
     *      *         "username": "ZoharAndroid"
     */

    private boolean admin;
    private List<String> chapterTops;
    private List<Integer> collectIds;
    private String email;
    private String icon;
    private int id;
    private String nickname;
    private String password;
    private String token;
    private int type;
    private String username;

    public List<String> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<String> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Register{" +
                "nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
