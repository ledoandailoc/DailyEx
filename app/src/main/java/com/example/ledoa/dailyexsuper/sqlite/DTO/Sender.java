package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by THUANCHATPC on 8/3/2015.
 */
public class Sender implements Serializable {
    public String _id;
    public String username;
    public String avatar;

    public Sender() {
    }

    public Sender(JSONObject json) throws JSONException {
        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("username")) {
            this.username = json.getString("username");
        }
        if (json.has("avatar")) {
            this.avatar = json.getString("avatar");
        }
    }
}
