package com.example.ledoa.dailyexsuper.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAnPref;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KhauPhanAnPref {

    private SharedPreferences mPref;
    private Editor mEditor;
    List<MonAnPref> listMonAnPref;
    private static final String KEY = "MonAnPref";

    public KhauPhanAnPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void addMonAn(MonAnPref monAnPref) {
        listMonAnPref = getListMonAnPref();
        listMonAnPref.add(monAnPref);
        String jsonUser = "";
        for (MonAnPref monAnPref1 : listMonAnPref) {
            Gson gson = new Gson();
            String u = gson.toJson(monAnPref1);
            jsonUser += (u + "zxc");
        }
        mEditor.putString(KEY, jsonUser);
        mEditor.commit();

    }

    public List<MonAnPref> getListMonAnPref() {
        String listMonAnPref = mPref.getString(KEY, null);
        if (listMonAnPref != null) {
            listMonAnPref.trim();
            String[] listUser = listMonAnPref.toString().split("zxc");
            List<MonAnPref> list = new ArrayList<MonAnPref>();
            for (String jsonUser: listUser) {
                MonAnPref s = parseUser(jsonUser);
                list.add(s);
            }
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public List<MonAnPref> getListMonAnPrefTheoBuoi(String buoi) {
        String listMonAnPref = mPref.getString(KEY, null);
        if (listMonAnPref != null) {
            listMonAnPref.trim();
            String[] listUser = listMonAnPref.toString().split("zxc");
            List<MonAnPref> list = new ArrayList<MonAnPref>();
            for (String jsonUser: listUser) {
                MonAnPref s = parseUser(jsonUser);
                if (s.buoi.equals(buoi)) {
                    list.add(s);
                }
            }
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public int getTongCalo(){
        int tongCalo = 0;
        listMonAnPref = getListMonAnPref();
        for (MonAnPref monAnPref:listMonAnPref) {
            tongCalo += monAnPref.calo;
        }

        return tongCalo;
    }

    private MonAnPref parseUser(String jsonUser) {
        try {
            return new MonAnPref(new JSONObject(jsonUser));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
