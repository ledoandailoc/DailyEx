package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Comment implements Serializable{
	public String _id;
	public User user;
	public String content;
	public Statistic statistic;

	public Comment() {
		user = new User();
	}

	public Comment(JSONObject json) throws JSONException {
		if(json.has("_id")){
			this._id = json.getString("_id");
		}
		user = new User();
		if(json.has("_userId")){
			user = new User(new JSONObject(json.getString("_userId")));
		}
		if(json.has("content")){
			this.content = json.getString("content");
		}
		statistic = new Statistic();
		if(json.has("statistic")){
			statistic = new Statistic(new JSONObject(json.getString("statistic")));
		}
	}
}
