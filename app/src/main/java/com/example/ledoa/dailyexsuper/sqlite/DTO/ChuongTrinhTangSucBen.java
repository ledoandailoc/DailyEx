package com.example.ledoa.dailyexsuper.sqlite.DTO;

/**
 * Created by ledoa on 9/9/2015.
 */
public class ChuongTrinhTangSucBen {

    int Id;
    String TenChuongTrinh;
    String MaMonTap;
    String NoiDung;
    int MucTieuQuangDuong;
    int MucTieuThoiGian;
    int HoanThanh;
    int Tuan;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        TenChuongTrinh = tenChuongTrinh;
    }

    public String getMaMonTap() {
        return MaMonTap;
    }

    public void setMaMonTap(String maMonTap) {
        MaMonTap = maMonTap;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getMucTieuQuangDuong() {
        return MucTieuQuangDuong;
    }

    public void setMucTieuQuangDuong(int mucTieuQuangDuong) {
        MucTieuQuangDuong = mucTieuQuangDuong;
    }

    public int getMucTieuThoiGian() {
        return MucTieuThoiGian;
    }

    public void setMucTieuThoiGian(int mucTieuThoiGian) {
        MucTieuThoiGian = mucTieuThoiGian;
    }

    public int getHoanThanh() {
        return HoanThanh;
    }

    public void setHoanThanh(int hoanThanh) {
        HoanThanh = hoanThanh;
    }

    public int getTuan() {
        return Tuan;
    }

    public void setTuan(int tuan) {
        Tuan = tuan;
    }
}
