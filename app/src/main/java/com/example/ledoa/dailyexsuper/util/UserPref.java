package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserPref {

    private SharedPreferences mPref;
    private Editor mEditor;

    private static final String KEY_USER = "user";
    private static final String KEY_CONTACT = "contact";

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

    public void saveListPhone(List<String> listPhone) {
        if (listPhone != null) {
            Gson gson = new Gson();
            String jsonListPhone = gson.toJson(listPhone);
            mEditor.putString(KEY_CONTACT, jsonListPhone);
            mEditor.commit();
        } else {
            mEditor.putString(KEY_CONTACT, null);
            mEditor.commit();
        }
    }

    public List<String> getListPhone() {
        List<String> listPhone = new ArrayList<>();
        String jsonListPhone = mPref.getString(KEY_CONTACT, null);
        if (jsonListPhone != null) {
            JSONArray jData = null;
            try {
                jData = new JSONArray(jsonListPhone);
                for (int i = 0; i < jData.length(); i++) {
                    listPhone.add(jData.get(i).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listPhone;
        } else {
            return null;
        }
    }

}
