package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Friend implements Serializable{
	public String _id;
	public String senderId;
	public String receiverId;
	public Boolean success;

	public Friend() {
	}
	public Friend(JSONObject json) throws JSONException{
		if(json.has("_id")){
			this._id = json.getString("_id");
		}
		if(json.has("senderId")){
			senderId = json.getString("senderId");
		}
		if(json.has("receiverId")){
			receiverId = json.getString("receiverId");
		}
		if(json.has("success")){
			this.success = json.getBoolean("success");
		}
	}
}
