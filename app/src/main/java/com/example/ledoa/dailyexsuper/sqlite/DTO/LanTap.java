package com.example.ledoa.dailyexsuper.sqlite.DTO;

public class LanTap  {
	int id;
	String tenMonTap;
	String ngay;
	int tongThoiGian;
	int soDongTac;
	double vanTocCaoNhat;
	double tocDoTrungBinh;
	int calo;
	
	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenMonTap() {
		return tenMonTap;
	}

	public void setTenMonTap(String tenMonTap) {
		this.tenMonTap = tenMonTap;
	}

	public int getTongThoiGian() {
		return tongThoiGian;
	}

	public void setTongThoiGian(int tongThoiGian) {
		this.tongThoiGian = tongThoiGian;
	}

	public int getSoDongTac() {
		return soDongTac;
	}

	public void setSoDongTac(int soDongTac) {
		this.soDongTac = soDongTac;
	}

	public double getVanTocCaoNhat() {
		return vanTocCaoNhat;
	}

	public void setVanTocCaoNhat(double d) {
		this.vanTocCaoNhat = d;
	}

	public double getTocDoTrungBinh() {
		return tocDoTrungBinh;
	}

	public void setTocDoTrungBinh(double d) {
		this.tocDoTrungBinh = d;
	}

	public int getCalo() {
		return calo;
	}

	public void setCalo(int calo) {
		this.calo = calo;
	}

	public LanTap(){
		
	}

	
}
