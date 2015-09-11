package com.example.ledoa.dailyexsuper.sqlite.DTO;

import android.graphics.Bitmap;

public class ItemThemBan {
	Bitmap avatar;/* = BitmapFactory.decodeResource(context.getResources(),
            R.drawable.avatar);*/
	String text;
	String textButton = "Kết bạn";
	int MyAccountId;
	int FriendAccountId;
	public Bitmap getAvatar() {
		return avatar;
	}
	public void setAvatar(Bitmap avatar) {
		this.avatar = avatar;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextButton() {
		return textButton;
	}
	public void setTextButton(String textButton) {
		this.textButton = textButton;
	}
	public int getMyAccountId() {
		return MyAccountId;
	}
	public void setMyAccountId(int myAccountId) {
		MyAccountId = myAccountId;
	}
	public int getFriendAccountId() {
		return FriendAccountId;
	}
	public void setFriendAccountId(int friendAccountId) {
		FriendAccountId = friendAccountId;
	}
}
