package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ledoa on 10/12/2015.
 */
public class MonAnPref implements Serializable{
    public int id;
    public int soLuong;
    public String donVi;
    public  String name;
    public String buoi;
    public int calo;
    public MonAnPref(){

    }

    public MonAnPref(JSONObject json) throws JSONException{
        if (json.has("id")) {
            this.id = json.getInt("id");
        }
        if (json.has("soLuong")){
            this.soLuong = json.getInt("soLuong");
        }
        if (json.has("name")){
            this.name = json.getString("name");
        }
        if (json.has("calo")){
            this.calo = json.getInt("calo");
        }
        if (json.has("buoi")){
            this.buoi = json.getString("buoi");
        }
        if (json.has("donVi")){
            this.donVi = json.getString("donVi");
        }
    }
}
