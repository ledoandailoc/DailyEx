package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.News;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewPref {

    private SharedPreferences mPref;
    private Editor mEditor;

    private String KEY_USER = "new";

    public NewPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void setListUser(List<News> listUser, String key) {
        KEY_USER = key;
        if (listUser != null) {
            String jsonUser = "";
            for (News user : listUser) {
                Gson gson = new Gson();
                String u = gson.toJson(user);
                jsonUser +=(u+"zxc");
            }
            mEditor.putString(KEY_USER, jsonUser);
            mEditor.commit();
        } else {
            mEditor.putString(KEY_USER, null);
            mEditor.commit();
        }
    }

    public List<News> getListUser(String key) {
        KEY_USER = key;
        String listJsonUser = mPref.getString(KEY_USER, null);
        if (listJsonUser != null) {
            listJsonUser.trim();
            String[] listUser = listJsonUser.toString().split("zxc");
            List<News> list = new ArrayList<News>();
            for (String jsonUser: listUser) {
                News s = parseUser(jsonUser);
                list.add(s);
            }
            return list;
        } else {
            return null;
        }
    }

    private News parseUser(String jsonUser) {
        try {
            return new News(new JSONObject(jsonUser));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
