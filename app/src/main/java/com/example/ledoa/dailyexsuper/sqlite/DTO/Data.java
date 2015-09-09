package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Data implements Serializable {

    public String _id;
    public String _roomId;
    public Message message;
    public User user;
    public String createdAt;
    public int typeDisplay;
    public int type;
    public String sequence;
    public int __v;
    public File file;

    public Data() {
        user = new User();
        file = new File();
        message = new Message();
    }

    public Data(JSONObject json) throws JSONException {
        user = new User();
        message = new Message();
        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("_roomId")) {
            this._roomId = json.getString("_roomId");
        }
        if (json.has("message")) {
            this.message.message = json.getString("message");
        }
        if (json.has("_userId")) {
            this.user = new User(new JSONObject(json.getString("_userId")));
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("__v")) {
            this.__v = json.getInt("__v");
        }
        if (json.has("type")) {
            this.type = json.getInt("type");
        }
        if (json.has("sequence")) {
            this.sequence = json.getString("sequence");
        }
        if (json.has("file")) {
            this.file = new File(new JSONObject(json.getString("file")));
        }
    }
}
