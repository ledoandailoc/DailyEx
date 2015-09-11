package com.example.ledoa.dailyexsuper.sqlite.DTO;

import android.graphics.Bitmap;

public class ItemMxh {
	Bitmap avatar,statusMxh;
	String tkhoan, tgian, status;
	public Bitmap getAvatar() {
		return avatar;
	}
	public void setAvatar(Bitmap avatar) {
		this.avatar = avatar;
	}
	public Bitmap getStatusMxh() {
		return statusMxh;
	}
	public void setStatusMxh(Bitmap statusMxh) {
		this.statusMxh = statusMxh;
	}
	public String getTkhoan() {
		return tkhoan;
	}
	public void setTkhoan(String tkhoan) {
		this.tkhoan = tkhoan;
	}
	public String getTgian() {
		return tgian;
	}
	public void setTgian(String tgian) {
		this.tgian = tgian;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
