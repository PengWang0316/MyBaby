package com.pengwang.mybaby.domain.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Peng on 3/19/2017.
 * Use to keep user name, id, and others
 */

public class User {
    private String id;
    private String name;
    private String facebookId;
    private String googleId;


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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getJsonString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",id).put("name",name).put("facebookId",facebookId).put("googleId",googleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
