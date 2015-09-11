package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Statistic implements Serializable {

    public int joiningEvent;
    public int joinedEvent;
    public int following;
    public int followers;

    public Statistic() {

    }

    public Statistic(JSONObject json) throws JSONException {
        if (json.has("joiningEvent")) {
            this.joiningEvent = json.getInt("joiningEvent");
        }
        if (json.has("joinedEvent")) {
            this.joinedEvent = json.getInt("joinedEvent");
        }
        if (json.has("following")) {
            this.following = json.getInt("following");
        }
        if (json.has("followers")) {
            this.followers = json.getInt("followers");
        }
    }

}
