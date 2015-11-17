package com.example.ledoa.dailyexsuper.sqlite.DTO;

import org.json.JSONObject;

/**
 * Created by ledoa on 10/21/2015.
 */
public class KhauPhanAn {
    String tenMonAn;
    String buoi;
    int calo;

    public KhauPhanAn(JSONObject jsonObject) {
    }


    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }
}
