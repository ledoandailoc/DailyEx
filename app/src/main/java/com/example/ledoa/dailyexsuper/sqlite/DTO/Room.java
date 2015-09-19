package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Room {

    public String _id;
    public String _userId;
    public String updatedAt;
    public String createdAt;
    public boolean isGroup;
    public boolean isNewRoom;
    public String title;
    public List<String> members;
    public int __v;
    public boolean isAdmin;

    public Room() {
        members = new ArrayList<>();
    }

    public Room(JSONObject json) throws JSONException {
        members = new ArrayList<>();

        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("_userId")) {
            this._userId = json.getString("_userId");
        }
        if (json.has("updatedAt")) {
            this.updatedAt = json.getString("updatedAt");
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("isGroup")) {
            this.isGroup = json.getBoolean("isGroup");
        }
        if (json.has("title")) {
            this.title = json.getString("title");
        }
        if (json.has("members")) {
            JSONArray jData = json.getJSONArray("members");
            for (int i = 0; i < jData.length(); i++) {
                members.add(jData.getString(i));
            }
        }
        if (json.has("__v")) {
            this.__v = json.getInt("__v");
        }
        if (json.has("isAdmin")) {
            this.isAdmin = json.getBoolean("isAdmin");
        }
    }

}
