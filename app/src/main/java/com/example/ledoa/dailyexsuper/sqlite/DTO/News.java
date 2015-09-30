package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class News implements Serializable {

    public String _id;
    public String _userId;
    public String description;
    public String title;
    public String thumbnail;
    public Statistic statistic;
    public String createdAt;
    public String content;

    public News(){
        statistic = new Statistic();
    }

    public News(JSONObject json) throws JSONException {
        if(json.has("_id")){
            this._id = json.getString("_id");
        }
        if(json.has("_userId")){
            this._userId = json.getString("_userId");
        }
        if(json.has("description")){
            this.description = json.getString("description");
        }
        if(json.has("title")){
            this.title = json.getString(("title"));
        }
        if(json.has("thumbnails")){
            this.thumbnail = json.getString("thumbnails");
        }
        if(json.has("createdAt")){
            this.createdAt = json.getString("createdAt");
        }
        if(json.has("statistic")){
            statistic = new Statistic(new JSONObject(json.getString("statistic")));
        }
        if(json.has("content")){
            this.content = json.getString("content");
        }
    }
}
