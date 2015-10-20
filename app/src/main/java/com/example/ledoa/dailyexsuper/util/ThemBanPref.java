package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThemBanPref {

    private SharedPreferences mPref;
    private Editor mEditor;

    private static final String KEY_USER = "themban";

    public ThemBanPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void setListUser(List<User> listUser) {
        if (listUser != null) {
            String jsonUser = "";
            for (User user : listUser) {
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

    public List<User> getListUser() {
        String listJsonUser = mPref.getString(KEY_USER, null);
        if (listJsonUser != null) {
            listJsonUser.trim();
            String[] listUser = listJsonUser.toString().split("zxc");
            List<User> list = new ArrayList<User>();
            for (String jsonUser: listUser) {
                User s = parseUser(jsonUser);
                list.add(s);
            }
            return list;
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
