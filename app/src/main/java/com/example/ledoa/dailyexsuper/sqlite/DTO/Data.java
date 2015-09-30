package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Data implements Serializable {

    public String _id;
    public String userId;
    public String message;
    public String createdAt;
    public int typeDisplay;
    public User user;
    public int __v;

    public Data() {
        user = new User();
    }

    public Data(JSONObject json) throws JSONException {

        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("message")) {
            this.message = json.getString("message");
        }
        if (json.has("_userId")) {
            this.userId = json.getString("_userId");
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("__v")) {
            this.__v = json.getInt("__v");
        }
        if (json.has("type")) {
            this.typeDisplay = json.getInt("type");
        }
        user = new User();
        if (json.has("user")) {
                user = new User(new JSONObject(json.getString("user")));
        }
    }
}
