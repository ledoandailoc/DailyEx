package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class File implements Serializable {

    public String _id;
    public String name;
    public String originalName;
    public String extension;
    public int size;
    public int type;
    public String createdAt;
    public String thumbnail;

    public File() {

    }

    public File(JSONObject json) throws JSONException {

        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
        if (json.has("name")) {
            this.name = json.getString("name");
        }
        if (json.has("originalName")) {
            this.originalName = json.getString("originalName");
        }
        if (json.has("extension")) {
            this.extension = json.getString("extension");
        }
        if (json.has("size")) {
            this.size = json.getInt("size");
        }
        if (json.has("type")) {
            this.type = json.getInt("type");
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("thumbnail")) {
            this.thumbnail = json.getString("thumbnail");
        }
    }

}
