package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {

    public String _id;
    public String username;
    public String cannang, chieucao;
    public int __v;
    public Statistic statistic;
    public String createdAt;
    public int role;
    public int gender;
    public boolean isPublicSeen;
    public int status;
    public String avatar;
    public boolean isFollow;
    public String token;
    public String latitude, longitude;
    public boolean isOnline;

    public User() {

    }

    public User(JSONObject json) throws JSONException {
        statistic = new Statistic();
        String s = json.toString();
        if (json.has("_id")) {
            this._id = json.getString("_id");
        }

        if (json.has("latitude")) {
            this.latitude = json.getString("latitude");
        }
        if (json.has("longitude")) {
            this.longitude = json.getString("longitude");
        }
        if (json.has("username")) {
            this.username = json.getString("username");
        }
        if (json.has("phone")) {
            this.chieucao = json.getString("phone");
        }
        if (json.has("country")) {
            this.cannang = json.getString("country");
        }
        if (json.has("__v")) {
            this.__v = json.getInt("__v");
        }
        if (json.has("statistic")) {
            this.statistic = new Statistic(new JSONObject(json.getString("statistic")));
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("role")) {
            this.role = json.getInt("role");
        }
        if (json.has("gender")) {
            this.gender = json.getInt("gender");
        }
        if (json.has("isPublicSeen")) {
            this.isPublicSeen = json.getBoolean("isPublicSeen");
        }
        if (json.has("status")) {
            this.status = json.getInt("status");
        }
        if (json.has("avatar")) {
            this.avatar = json.getString("avatar");
        }
        if (json.has("isFollow")) {
            this.isFollow = json.getBoolean("isFollow");
        }
        if (json.has("token")) {
            this.token = json.getString("token");
        }
        if (json.has("isOnline")) {
            this.isOnline = json.getBoolean("isOnline");
        }
    }
}
