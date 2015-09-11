package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Message implements Serializable {

    public String _id;
    public String message;
    public String createdAt;
    public String createdAt_format;
    public int type;
    public File file;
    public User user;
    public String _roomId;
    public Message() {
        file = new File();
        user = new User();
    }

    public Message(JSONObject json) throws JSONException {

        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("_roomId")){
            this._roomId = json.getString("_roomId");
        }
        if (json.has("message")) {
            this.message = json.getString("message");
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("type")) {
            this.type = json.getInt("type");
        }
        if (json.has("file")) {
            this.file = new File(new JSONObject(json.getString("file")));
        }
        if (json.has("_userId")){
            this.user= new User(new JSONObject(json.getString("_userId")));
        }
        if (json.has("createdAt_format")) {
            this.createdAt_format = json.getString("createdAt_format");
        }
    }

}
