package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoPref {


    private SharedPreferences mPref;
    private Editor mEditor;

    private static final String KEY_USER = "thongbao";

    public ThongBaoPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void setListUser(List<ThongBao> listUser) {
        if (listUser != null) {
            String jsonUser = "";
            for (ThongBao user : listUser) {
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

    public List<ThongBao> getListUser() {
        String listJsonUser = mPref.getString(KEY_USER, null);
        if (listJsonUser != null) {
            listJsonUser.trim();
            String[] listUser = listJsonUser.toString().split("zxc");
            List<ThongBao> list = new ArrayList<ThongBao>();
            for (String jsonUser: listUser) {
                ThongBao s = parseUser(jsonUser);
                list.add(s);
            }
            return list;
        } else {
            return null;
        }
    }

    private ThongBao parseUser(String jsonUser) {
        try {
            return new ThongBao(new JSONObject(jsonUser));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
