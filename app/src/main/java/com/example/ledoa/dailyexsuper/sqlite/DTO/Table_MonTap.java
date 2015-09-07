package com.example.ledoa.dailyexsuper.sqlite.DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Table_MonTap extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "DailyExcercise";
	private static int DATABASE_VERSION = 1 ;
	public static String TABLE_MONTAP = "MonTap";
	public static String KEY_ID = "Id";
	public static String KEY_TEN = "Hoten";
	
	Context context;
	
	
	public Table_MonTap(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	public List<MonTap> getMonTap(){
		List<MonTap> list = new ArrayList<MonTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_MONTAP;
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			MonTap montap = new MonTap();
			montap.setId(cursor.getString(0));
			montap.setTenMonTap(cursor.getString(1));
			list.add(montap);
			
			cursor.moveToNext();
		}
		
		return list;
	}
	
	public void themMonTap(MonTap monTap){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, monTap.getId());
		values.put(KEY_TEN, monTap.getTenMonTap());
		
		if(db.insert(TABLE_MONTAP, null, values)!= -1){
			Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
		}
		db.close();
	}
	

	public void xoadulieu(String id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MONTAP, KEY_ID +" =?", new String[]{id});
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String taoBangLienHe = "create table " + TABLE_MONTAP + " ( " + KEY_ID +" Integer primary key, " + KEY_TEN + " text " + ")";
		db.execSQL(taoBangLienHe);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_MONTAP);
		onCreate(db);
	}

}



