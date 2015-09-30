package com.example.ledoa.dailyexsuper.sqlite.DTO;

/**
 * Created by ledoa on 9/9/2015.
 */
public class ChuongTrinhGiamCan {

    int Id;
    String TenChuongTrinh;
    String NoiDungChuongTrinh;
    String MaMonTap;
    String Ngay;
    int CanNangHienTai;
    int CanNangMucTieu;
    int SoCanNangCatGiam;
    int SoNgayTap;
    double SoGioMoiNgay;
    int TienDo;

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

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getSoCanNangCatGiam() {
        return SoCanNangCatGiam;
    }

    public void setSoCanNangCatGiam(int soCanNangCatGiam) {
        SoCanNangCatGiam = soCanNangCatGiam;
    }

    public int getSoNgayTap() {
        return SoNgayTap;
    }

    public void setSoNgayTap(int soNgayTap) {
        SoNgayTap = soNgayTap;
    }

    public double getSoGioMoiNgay() {
        return SoGioMoiNgay;
    }

    public void setSoGioMoiNgay(double soGioMoiNgay) {
        SoGioMoiNgay = soGioMoiNgay;
    }

    public int getTienDo() {
        return TienDo;
    }

    public void setTienDo(int tienDo) {
        TienDo = tienDo;
    }

    public String getNoiDungChuongTrinh() {
        return NoiDungChuongTrinh;
    }

    public void setNoiDungChuongTrinh(String noiDungChuongTrinh) {
        NoiDungChuongTrinh = noiDungChuongTrinh;
    }

    public int getCanNangHienTai() {
        return CanNangHienTai;
    }

    public void setCanNangHienTai(int canNangHienTai) {
        CanNangHienTai = canNangHienTai;
    }

    public int getCanNangMucTieu() {
        return CanNangMucTieu;
    }

    public void setCanNangMucTieu(int canNangMucTieu) {
        CanNangMucTieu = canNangMucTieu;
    }
}
