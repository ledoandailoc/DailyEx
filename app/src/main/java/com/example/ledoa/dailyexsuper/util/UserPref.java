package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPref {

    private SharedPreferences mPref;
    private Editor mEditor;

    private static final String KEY_USER = "user";

    public UserPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void setUser(User user) {
        if (user != null) {
            Gson gson = new Gson();
            String jsonUser = gson.toJson(user);
            mEditor.putString(KEY_USER, jsonUser);
            mEditor.commit();
        } else {
            mEditor.putString(KEY_USER, null);
            mEditor.commit();
        }
    }

    public User getUser() {
        String jsonUser = mPref.getString(KEY_USER, null);
        if (jsonUser != null) {
            return parseUser(jsonUser);
        } else {
            return null;
        }
    }

    private User parseUser(String jsonUser) {
        try {
            return new User(new JSONObject(jsonUser));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
