package com.example.ledoa.dailyexsuper.sqlite.DTO;

/**
 * Created by ledoa on 9/9/2015.
 */
public class BaiTap {

public static int Id;
    String LoaiMucTieu;
    String TenBaiTap;
    String LoaiBaiTap;
    int MucTieu;
    int HoanThanh;

    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        Id = id;
    }

    public String getLoaiMucTieu() {
        return LoaiMucTieu;
    }

    public void setLoaiMucTieu(String maBaiTap) {
        LoaiMucTieu = maBaiTap;
    }

    public String getTenBaiTap() {
        return TenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        TenBaiTap = tenBaiTap;
    }

    public String getLoaiBaiTap() {
        return LoaiBaiTap;
    }

    public void setLoaiBaiTap(String loaiBaiTap) {
        LoaiBaiTap = loaiBaiTap;
    }

    public int getMucTieu() {
        return MucTieu;
    }

    public void setMucTieu(int mucTieu) {
        MucTieu = mucTieu;
    }

    public int getHoanThanh() {
        return HoanThanh;
    }

    public void setHoanThanh(int hoanThanh) {
        HoanThanh = hoanThanh;
    }
}
