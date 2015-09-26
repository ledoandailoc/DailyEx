package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ThongBao implements Serializable{
	public String _id;
	public User user;
	public Friend friend;
	public News news;

	public ThongBao() {
		user = new User();
		friend = new Friend();
		news = new News();
	}

	public ThongBao(JSONObject json) throws JSONException {
		if(json.has("_id")){
			this._id = json.getString("_id");
		}
		user = new User();
		if(json.has("_userId")){
			user = new User(new JSONObject(json.getString("_userId")));
		}
		friend = new Friend();
		if(json.has("_friendId")){
			friend = new Friend(new JSONObject(json.getString("_friendId")));
		}
		news = new News();
		if(json.has("_newsId")){
			news = new News(new JSONObject(json.getString("_newsId")));
		}
	}
}
