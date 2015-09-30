package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class PublicData implements Serializable {

    public Boolean isLike;
    public String commentId;

    public PublicData(){

    }

    public PublicData(JSONObject json) throws JSONException {
        if(json.has("isLikes")){
            this.isLike = json.getBoolean("islikes");
        }
        if(json.has("commentId")){
            this.commentId = json.getString("commentId");
        }

    }
}
