package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Statistic implements Serializable {

    public int comments;
    public int likes;

    public Statistic() {

    }

    public Statistic(JSONObject json) throws JSONException {
        if (json.has("comments")) {
            this.comments = json.getInt("comments");
        }
        if (json.has("likes")) {
            this.likes = json.getInt("likes");
        }

    }

}
