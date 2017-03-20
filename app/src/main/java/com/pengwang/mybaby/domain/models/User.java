package com.pengwang.mybaby.domain.models;

/**
 * Created by Peng on 3/19/2017.
 * Use to keep user name, id, and others
 */

public class User {
    private String id;
    private String name;
    private String facebookId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
