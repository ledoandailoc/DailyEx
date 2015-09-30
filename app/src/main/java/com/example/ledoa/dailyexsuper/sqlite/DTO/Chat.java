package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Chat implements Serializable {

    public Message message;
    public Sender sender;
    public String sequence;
    public Room room;
    public User user;

    public Chat() {
        message = new Message();
        user = new User();
    }

    public Chat(JSONObject json) throws JSONException {
        message = new Message();
        if (json.has("message")) {
            this.message = new Message(new JSONObject(json.getString("message")));
        }
        user = new User();
        if (json.has("user")) {
                user = new User(new JSONObject(json.getString("user")));
        }
    }
}
