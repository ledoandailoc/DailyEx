package com.example.ledoa.dailyexsuper.util;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


public class ScaleImage {
	
	public static void SetImage(Resources rs, ImageView button, int path, int weigth, int heigh) {
		Bitmap bm = BitmapFactory.decodeResource(rs, path);
		bm = Bitmap.createScaledBitmap(bm, weigth, heigh, true);
		button.setImageBitmap(bm);
		
	}
}
