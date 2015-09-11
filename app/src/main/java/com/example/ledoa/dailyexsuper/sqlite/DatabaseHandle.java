package com.example.ledoa.dailyexsuper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.LanTap;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "DailyExcercise";
	private static int DATABASE_VERSION = 4 ;
	public static String TABLE_MONTAP = "LanTap";
	public static String KEY_ID = "Id";
	public static String KEY_NGAYTAP = "Ngay";
	public static String KEY_IDMONTAP = "IdMonTap";
	public static String KEY_TONGTHOIGIAN = "TongThoiGian";
	public static String KEY_SODONGTAC = "SoDongTac";
	public static String KEY_TOCDOCAONHAT = "TocDoCaoNhat";
	public static String KEY_TOCDOTRUNGBINH = "TocDoTrungBinh";
	public static String KEY_CALO = "Calo";

	public static String TABLE_BAITAP = "BaiTap";
	public static String KEY_LOAIMUCTIEU = "LoaiMucTieu";
	public static String KEY_IDBAITAP = "IdBaiTap";
	public static String KEY_TENBAITAP = "TenBaiTap";
	public static String KEY_LOAIBAITAP = "LoaiBaiTap";
	public static String KEY_MUCTIEU = "MucTieu";
	public static String KEY_HOANTHANH = "HoanThanh";
	
	public static String KEY_TINHTHOIGIAN = "TinhThoiGian";
	Context context;
	
	
	public DatabaseHandle(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	
	
	
	public String getTenMonTapTheoNgay(String date) {
		String monTap = "";
		List<LanTap> list = new ArrayList<LanTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select distinct " + KEY_IDMONTAP + " from " + TABLE_MONTAP + " where " + KEY_NGAYTAP + " like '%"+ date +"%'"  ;
		try {
			Cursor cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				String tenMonTap = "";
				if ((cursor.getString(cursor.getColumnIndex(KEY_IDMONTAP))).equals("DB"))
					tenMonTap = "Đi bộ";
				else if ((cursor.getString(cursor.getColumnIndex(KEY_IDMONTAP))).equals("CB"))
					tenMonTap = "Chạy bộ";
				else if ((cursor.getString(cursor.getColumnIndex(KEY_IDMONTAP))).equals("DX"))
					tenMonTap = "Đạp xe";
				else if ((cursor.getString(cursor.getColumnIndex(KEY_IDMONTAP))).equals("HD"))
					tenMonTap = "Hít đất";
				monTap =  monTap + ", " + tenMonTap;
				
				cursor.moveToNext();
			}
			
			monTap = monTap.substring(1);
		} catch (Exception e) {
			monTap = "" +
					"Không có môn tập nào";
		}
		
		return monTap;
	}
	
	public int tinhTongThoiGianTapTrongNgay(String date) {
		int tongThoiGian = 0;
		List<LanTap> list = new ArrayList<LanTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			String sql = "select sum( "+ KEY_TONGTHOIGIAN +" ) as " + KEY_TINHTHOIGIAN + " from " + TABLE_MONTAP + " where " + KEY_NGAYTAP + " like '%"+ date +"%'"  ;
			Cursor cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			tongThoiGian = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TINHTHOIGIAN)));
		} catch (Exception e) {
			tongThoiGian = 0;
		}
		return tongThoiGian;
	}
	
	public List<LanTap> getLanTapTheoNgay(String date) {
		List<LanTap> list = new ArrayList<LanTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_MONTAP + " where " + KEY_NGAYTAP + " like '%"+ date +"%'"  ;
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			LanTap lanTap = new LanTap();
			
			lanTap.setId(Integer.parseInt(cursor.getString(0)));
			lanTap.setTenMonTap(cursor.getString(1));
			lanTap.setNgay(cursor.getString(2));
			lanTap.setTongThoiGian(Integer.parseInt(cursor.getString(3)));
			lanTap.setSoDongTac(Integer.parseInt(cursor.getString(4)));
			lanTap.setVanTocCaoNhat(Double.parseDouble(cursor.getString(5)));
			lanTap.setTocDoTrungBinh(Double.parseDouble(cursor.getString(6)));
			lanTap.setCalo(Integer.parseInt(cursor.getString(7)));
			
			list.add(lanTap);
			
			cursor.moveToNext();
		}
		
		return list;
	}

	public List<LanTap> getLanTapTheoNgayMon(String date,String mon) {
		List<LanTap> list = new ArrayList<LanTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_MONTAP + " where (" + KEY_NGAYTAP + " like '%"+ date +"%'"
				+ " and " + KEY_IDMONTAP + " like '%"+ mon +"%')";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			LanTap lanTap = new LanTap();

			lanTap.setId(Integer.parseInt(cursor.getString(0)));
			lanTap.setTenMonTap(cursor.getString(1));
			lanTap.setNgay(cursor.getString(2));
			lanTap.setTongThoiGian(Integer.parseInt(cursor.getString(3)));
			lanTap.setSoDongTac(Integer.parseInt(cursor.getString(4)));
			lanTap.setVanTocCaoNhat(Double.parseDouble(cursor.getString(5)));
			lanTap.setTocDoTrungBinh(Double.parseDouble(cursor.getString(6)));
			lanTap.setCalo(Integer.parseInt(cursor.getString(7)));

			list.add(lanTap);

			cursor.moveToNext();
		}

		return list;
	}

	public List<LanTap> getLanTap(){
		List<LanTap> list = new ArrayList<LanTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_MONTAP;
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			LanTap lanTap = new LanTap();
			
			lanTap.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
			lanTap.setTenMonTap(cursor.getString(1));
			lanTap.setNgay(cursor.getString(2));
			lanTap.setTongThoiGian(Integer.parseInt(cursor.getString(3)));
			lanTap.setSoDongTac(Integer.parseInt(cursor.getString(4)));
			lanTap.setVanTocCaoNhat(Long.parseLong(cursor.getString(5)));
			lanTap.setTocDoTrungBinh(Long.parseLong(cursor.getString(6)));
			lanTap.setCalo(Integer.parseInt(cursor.getString(7)));
			
			list.add(lanTap);
			
			cursor.moveToNext();
		}
		
		return list;
	}
	
	public void addMonTap(LanTap lanTap){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		/*values.put(KEY_ID, lanTap.getId());*/
		values.put(KEY_IDMONTAP, lanTap.getTenMonTap());
		values.put(KEY_NGAYTAP, lanTap.getNgay());
		values.put(KEY_TONGTHOIGIAN, lanTap.getTongThoiGian());
		values.put(KEY_SODONGTAC, lanTap.getSoDongTac());
		values.put(KEY_TOCDOCAONHAT, lanTap.getVanTocCaoNhat());
		values.put(KEY_TOCDOTRUNGBINH, lanTap.getTocDoTrungBinh());
		values.put(KEY_CALO, lanTap.getCalo());
		

		if(db.insert(TABLE_MONTAP, null, values)!= -1){
			Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
		}
		db.close();
	}
	

	public void xoadulieu(String id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MONTAP, KEY_ID + " =?", new String[]{id});
		db.close();
	}

	/*Truy van bai tap*/

	public List<BaiTap> getBaiTapTheoLoai(String loaibaitap, String loaimuctieu) {
		List<BaiTap> list = new ArrayList<BaiTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select distinct * from " + TABLE_BAITAP + " where (" + KEY_LOAIBAITAP + " like '"+ loaibaitap +"'"
				+ " and " + KEY_LOAIMUCTIEU + " like '"+ loaimuctieu +"')";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			BaiTap baiTap = new BaiTap();

			baiTap.setId(Integer.parseInt(cursor.getString(0)));
			baiTap.setLoaiMucTieu(cursor.getString(1));
			baiTap.setTenBaiTap(cursor.getString(2));
			baiTap.setLoaiBaiTap(cursor.getString(3));
			baiTap.setMucTieu(Integer.parseInt(cursor.getString(4)));
			baiTap.setHoanThanh(Integer.parseInt(cursor.getString(5)));

			list.add(baiTap);

			cursor.moveToNext();
		}

		return list;
	}

	public BaiTap getBaiTapTheoId(int id) {
		List<BaiTap> list = new ArrayList<BaiTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select distinct * from " + TABLE_BAITAP + " where (" + KEY_IDBAITAP + " like '"+ id + "')";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
			BaiTap baiTap = new BaiTap();

			baiTap.setId(Integer.parseInt(cursor.getString(0)));
			baiTap.setLoaiMucTieu(cursor.getString(1));
			baiTap.setTenBaiTap(cursor.getString(2));
			baiTap.setLoaiBaiTap(cursor.getString(3));
			baiTap.setMucTieu(Integer.parseInt(cursor.getString(4)));
			baiTap.setHoanThanh(Integer.parseInt(cursor.getString(5)));

			list.add(baiTap);

			cursor.moveToNext();


		return baiTap;
	}

	public void updateBaiTap(String id) {
		List<BaiTap> list = new ArrayList<BaiTap>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "UPDATE " + TABLE_BAITAP + " SET " + KEY_HOANTHANH + " = 1" +  " where (" + KEY_TENBAITAP + " like '"+ id + "')";

		db.execSQL(sql);
	}

	public void autoInsertDataBase()
	{
		getWritableDatabase().execSQL((new StringBuilder())
				.append("INSERT INTO ")
				.append(TABLE_MONTAP)
				.append(" (Ngay, IdMonTap, TongThoiGian, SoDongTac, TocDoCaoNhat, TocDoTrungBinh, Calo)")
				.append(" VALUES")
				.append(" ('02-07-2015 11:22:33', 'DB', 300, 2, 3.0, 1.5, 0),")
				.append(" ('01-07-2015 11:22:33', 'DB', 500, 2, 3.0, 1.5, 0),")
				.append(" ('01-07-2015 11:22:33', 'CB', 500, 2, 3.0, 1.5, 0),")
				.append(" ('01-07-2015 11:22:33', 'DX', 600, 2, 3.0, 1.5, 0),")
				.append(" ('01-07-2015 11:22:33', 'HD', 200, 2, 3.0, 1.5, 0),")
				.append(" ('01-07-2015 11:22:33', 'DX', 400, 2, 3.0, 1.5, 0),")
				.append(" ('08-07-2015 11:22:33', 'DB', 2000, 2, 3.0, 1.5, 0),")
				.append(" ('09-07-2015 11:22:33', 'DB', 3600, 2, 3.0, 1.5, 0),")
				.append(" ('15-07-2015 11:22:33', 'DB', 1000, 2, 3.0, 1.5, 0),")
				.append(" ('31-07-2015 11:22:33', 'CB', 500, 2, 3.0, 1.0, 0);")
				.toString());


		/*getWritableDatabase().execSQL((new StringBuilder())
				.append("INSERT INTO ")
				.append(TABLE_BAITAP)
				.append(" (LoaiMucTieu, TenBaiTap, LoaiBaiTap, MucTieu, HoanThanh)")
				.append(" VALUES")
				.append(" ('B', 'Đi bộ 100 bước', 'DB', 100, 1),")
				.append(" ('B', 'Đi bộ 200 bước', 'DB', 200, 1),")
				.append(" ('B', 'Đi bộ 300 bước', 'DB', 300, 0),")
				.append(" ('B', 'Đi bộ 400 bước', 'DB', 400, 0),")
				.append(" ('B', 'Đi bộ 500 bước', 'DB', 400, 0),")

				.append(" ('TG', 'Đi bộ 30 phút', 'DB', 5, 0),")
				.append(" ('TG', 'Đi bộ 1h', 'DB', 3600, 1),")
				.append(" ('TG', 'Đi bộ 1h 30 phút ', 'DB', 5400, 0),")
				.append(" ('TG', 'Đi bộ 2h', 'DB', 7200, 0);")
				.toString());*/
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("drop table if exists " + TABLE_MONTAP);
		String taoBangLienHe = "create table " + TABLE_MONTAP
				+ " ( "
				+ KEY_ID + " Integer primary key, "
				+ KEY_IDMONTAP + " text, "
				+ KEY_NGAYTAP + " text, "
				+ KEY_TONGTHOIGIAN + " Integer, "
				+ KEY_SODONGTAC + " Integer, "
				+ KEY_TOCDOCAONHAT + " Real, "
				+ KEY_TOCDOTRUNGBINH + " Real, "
				+ KEY_CALO + " Integer"
				+ " ) ";

		String taoBangBaiTap = "create table " + TABLE_BAITAP
				+ " ( "
				+ KEY_IDBAITAP +" Integer primary key, "
				+ KEY_LOAIMUCTIEU +" text, "
				+ KEY_TENBAITAP + " text, "
				+ KEY_LOAIBAITAP + " text, "
				+ KEY_MUCTIEU + " Integer, "
				+ KEY_HOANTHANH + " Integer "
				+ " ) ";

		db.execSQL(taoBangLienHe);
		db.execSQL(taoBangBaiTap);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_MONTAP);
		db.execSQL("drop table if exists " + TABLE_BAITAP);
		onCreate(db);
	}

}



