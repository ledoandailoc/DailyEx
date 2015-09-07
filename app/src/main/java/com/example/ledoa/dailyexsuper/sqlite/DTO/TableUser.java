package com.example.ledoa.dailyexsuper.sqlite.DTO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TableUser extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "DailyExcercise";
	private static int DATABASE_VERSION = 1 ;
	public static String TABLE_USER= "User";
	public static String KEY_ID = "id";
	Context context;
	
	
	public TableUser(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	public void themUser(User user) {
		

	}
	

	
	
	
	public int getId() {
		List<User> list = new ArrayList<User>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " +TABLE_USER;
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		int id = 0;
		while(!cursor.isAfterLast()){
			User user = new User();
			id = user.getId();
			
			cursor.moveToNext();
		}
		return id; 
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String taoBangLienHe = "create table " + TABLE_USER + " ( " + KEY_ID +" Integer primary key " + ")";
		db.execSQL(taoBangLienHe);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_USER);
		onCreate(db);
	}

}



