package com.example.ledoa.dailyexsuper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;
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

	public static String TABLE_CHUONGTRINHGIAMCAN = "ChuongTrinhGiamCan";
	public static String KEY_IDCHUONGTRINH = "IdChuongTrinh";
	public static String KEY_TENCHUONGTRINH = "TenChuongTrinh";
	public static String KEY_NOIDUNGCHUONGTRINH = "NoiDungChuongTrinh";
	public static String KEY_NGAYTAOCHUONGTRINH = "NgayTaoChuongTrinh";
	public static String KEY_CANNANGHIENTAI = "CanNangHienTai";
	public static String KEY_CANNANGMUCTIEU = "CanNangMucTieu";
	public static String KEY_MAMONTAP = "MaMonTap";
	public static String KEY_SOCANNANGCATGIAM = "SoCanNangCatGiam";
	public static String KEY_SOGIOMOINGAY = "SoGioMoiNgay";
	public static String KEY_SONGAYTAP = "SoNgayTap";
	public static String KEY_TIENDO = "TienDo";


	public static String TABLE_CHUONGTRINHTANGSUCBEN= "ChuongTrinhTangSucBen";
//	public static String KEY_IDCHUONGTRINH = "IdChuongTrinh";
//	public static String KEY_TENCHUONGTRINH = "TenChuongTrinh";
//	public static String KEY_MAMONTAP = "MaMonTap";
//	public static String KEY_NOIDUNGCHUONGTRINH = "NoiDungChuongTrinh";
	public static String KEY_MUCTIEUQUANGDUONG = "MucTieuQuangDuong";
	public static String KEY_MUCTIEUTHOIGIAN = "MucTieuThoiGian";
//	public static String KEY_HOANTHANH = "HoanThanh";
	public static String KEY_TUAN = "Tuan";

	Context context;
	
	public DatabaseHandle(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	/*
	Auto insertdata
	*/
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


		getWritableDatabase().execSQL((new StringBuilder())
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
				.toString());

		getWritableDatabase().execSQL((new StringBuilder())
				.append("INSERT INTO ")
				.append(TABLE_CHUONGTRINHGIAMCAN)
				.append(" (TenChuongTrinh, NoiDungChuongTrinh, NgayTaoChuongTrinh, MaMonTap, CanNangHienTai, CanNangMucTieu, SoCanNangCatGiam, SoGioMoiNgay, SoNgayTap, TienDo)")
				.append(" VALUES")
				.append(" ('Đi bộ giảm cân', 'Đi bộ mỗi ngày 1.2 giờ trong vòng 56 ngày', '01-07-2015 11:22:33', 'DB', 70, 66, 5, 1.2, 56, 56),")
				.append(" ('Đi bộ giảm cân', 'Đi bộ mỗi ngày 0.001 giờ trong vòng 56 ngày', '01-07-2015 11:22:33', 'DB', 70, 66, 5, 0.001, 56, 0);")
				.toString());

		getWritableDatabase().execSQL((new StringBuilder())
				.append("INSERT INTO ")
				.append(TABLE_CHUONGTRINHTANGSUCBEN)
				.append(" (TenChuongTrinh, MaMonTap, NoiDungChuongTrinh, MucTieuQuangDuong, MucTieuThoiGian, HoanThanh, Tuan)")
				.append(" VALUES")
				.append(" ('2.5 km Đi bộ/Chạy bộ', 'DBCB', 'Chạy Chậm 2,5 km mệt thì đi bộ nhanh', 2500, 0, 1, 1),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ (tuyệt đối không chạy 2 ngày liên tiếp)', 0, 0, 1, 1),")
				.append(" ('2.5 km Đi bộ/Chạy bộ', 'DBCB', 'Chạy Chậm 2,5 km mệt thì đi bộ nhanh', 2500, 0, 1, 1),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 1),")
				.append(" ('2.5 km Đi bộ/Chạy bộ', 'DBCB', 'Cố Chạy Chậm 2,5 km hạn chế đi bộ', 2500, 0, 0, 1),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 1),")
				.append(" ('40 - 45 phút Đi bộ', 'DB', 'Đi bộ nhanh trong vòng 45 phút không nghỉ', 0, 2700, 0, 1),")

				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 2),")
				.append(" ('3 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3 km, hạn chế đi bộ', 3000, 0, 0, 2),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 2),")
				.append(" ('3 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3 km, hạn chế đi bộ', 3000, 0, 0, 2),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 2),")
				.append(" ('3 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3 km, hạn chế đi bộ', 3000, 0, 0, 2),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 2),")

				.append(" ('45 phút Đi bộ', 'DB', 'Đi bộ nhanh trong vòng 45 phút không nghỉ', 0, 2700, 0, 3),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 3),")
				.append(" ('3.4 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3.4 km, hạn chế đi bộ', 3400, 0, 0, 3),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 3),")
				.append(" ('3.4 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3.4 km, hạn chế đi bộ', 3400, 0, 0, 3),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 3),")
				.append(" ('3.4 km Chạy bộ', 'CB', 'Chạy Chậm ít nhất 3.4 km, hạn chế đi bộ', 3400, 0, 0, 3),")

				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 4),")
				.append(" ('50 phút Đi bộ', 'DB', 'Đi bộ nhanh trong vòng 50 phút không nghỉ', 0, 3000, 0, 4),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 4),")
				.append(" ('4 km Chạy bộ', 'CB', 'Chạy Chậm 4 km', 4000, 0, 0, 4),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 4),")
				.append(" ('4 km Chạy bộ', 'CB', 'Chạy Chậm 4 km', 4000, 0, 0, 4),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 4),")

				.append(" ('4 km Chạy bộ', 'CB', 'Chạy Chậm 5 km', 4000, 0, 0, 5),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 5),")
				.append(" ('5 km Chạy bộ', 'CB', 'Chạy Chậm 5 km', 5000, 0, 0, 5),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 5),")
				.append(" ('5 km Chạy bộ', 'CB', 'Chạy Chậm 5 km', 5000, 0, 0, 5),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 5),")
				.append(" ('50 phút Đi bộ', 'DB', 'Đi bộ nhanh trong vòng 50 phút không nghỉ', 0, 3000, 0, 5),")

				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 6),")
				.append(" ('5 km Chạy bộ', 'CB', 'Chạy Chậm 5 km', 5000, 0, 0, 6),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 6),")
				.append(" ('6 km Chạy bộ', 'CB', 'Chạy Chậm 6 km', 6000, 0, 0, 6),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 6),")
				.append(" ('6 km Chạy bộ', 'CB', 'Chạy Chậm 6 km', 6000, 0, 0, 6),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 6),")

				.append(" ('50 - 55 phút Đi bộ', 'DB', 'Đi bộ nhanh trong vòng 55 phút không nghỉ', 0, 3300, 0, 7),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 7),")
				.append(" ('7 km Chạy bộ', 'CB', 'Chạy Chậm 7 km', 7000, 0, 0, 7),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 7),")
				.append(" ('7 km Chạy bộ', 'CB', 'Chạy Chậm 7 km', 7000, 0, 0, 7),")
				.append(" ('Nghỉ ngơi', '', 'Nghĩ hoặc tập tạ hoặc đi bộ, bơi lội...', 0, 0, 0, 7),")
				.append(" ('> 7 km Chạy bộ', 'CB', 'Chạy Chậm 7 km', 7000, 0, 0, 7);")

				.toString());


	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("drop table if exists " + TABLE_MONTAP);
		String taoMonTap = "create table " + TABLE_MONTAP
				+ " ( "
				+ KEY_ID + " Integer primary key AUTOINCREMENT, "
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
				+ KEY_IDBAITAP +" Integer primary key AUTOINCREMENT, "
				+ KEY_LOAIMUCTIEU +" text, "
				+ KEY_TENBAITAP + " text, "
				+ KEY_LOAIBAITAP + " text, "
				+ KEY_MUCTIEU + " Integer, "
				+ KEY_HOANTHANH + " Integer "
				+ " ) ";

		String taoChuongTrinhTap = "create table " + TABLE_CHUONGTRINHTANGSUCBEN
				+ " ( "
				+ KEY_IDCHUONGTRINH +" Integer primary key AUTOINCREMENT, "
				+ KEY_TENCHUONGTRINH + " text, "
				+ KEY_MAMONTAP + " text, "
				+ KEY_NOIDUNGCHUONGTRINH +" text, "
				+ KEY_MUCTIEUQUANGDUONG + " Integer, "
				+ KEY_MUCTIEUTHOIGIAN + " Integer, "
				+ KEY_HOANTHANH + " Integer, "
				+ KEY_TUAN + " Integer "
				+ " ) ";

		String taoChuongTrinhTangSucBen = "create table " + TABLE_CHUONGTRINHGIAMCAN
				+ " ( "
				+ KEY_IDCHUONGTRINH +" Integer primary key AUTOINCREMENT, "
				+ KEY_TENCHUONGTRINH + " text, "
				+ KEY_NOIDUNGCHUONGTRINH +" text, "
				+ KEY_NGAYTAOCHUONGTRINH + " text, "
				+ KEY_MAMONTAP + " text, "
				+ KEY_CANNANGHIENTAI + " Integer, "
				+ KEY_CANNANGMUCTIEU + " Integer, "
				+ KEY_SOCANNANGCATGIAM + " Integer, "
				+ KEY_SOGIOMOINGAY + " Real, "
				+ KEY_SONGAYTAP + " Integer, "
				+ KEY_TIENDO + " Integer "
				+ " ) ";

		db.execSQL(taoMonTap);
		db.execSQL(taoBangBaiTap);
		db.execSQL(taoChuongTrinhTap);
		db.execSQL(taoChuongTrinhTangSucBen);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_MONTAP);
		db.execSQL("drop table if exists " + TABLE_BAITAP);
		onCreate(db);
	}

	/*
	Truy van chuong trinh tang suc ben
	*/
	public List<ChuongTrinhTangSucBen> getChuongTrinhTangSucBen() {
		List<ChuongTrinhTangSucBen> list = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_CHUONGTRINHTANGSUCBEN ;

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			ChuongTrinhTangSucBen chuongTrinhTangSucBen = new ChuongTrinhTangSucBen();

			chuongTrinhTangSucBen.setId(Integer.parseInt(cursor.getString(0)));
			chuongTrinhTangSucBen.setTenChuongTrinh(cursor.getString(1));
			chuongTrinhTangSucBen.setMaMonTap(cursor.getString(2));
			chuongTrinhTangSucBen.setNoiDung(cursor.getString(3));
			chuongTrinhTangSucBen.setMucTieuQuangDuong(Integer.parseInt(cursor.getString(4)));
			chuongTrinhTangSucBen.setMucTieuThoiGian(Integer.parseInt(cursor.getString(5)));
			chuongTrinhTangSucBen.setHoanThanh(Integer.parseInt(cursor.getString(6)));
			chuongTrinhTangSucBen.setTuan(Integer.parseInt(cursor.getString(7)));
			list.add(chuongTrinhTangSucBen);

			cursor.moveToNext();

		}

		return list;
	}

	/*
	Truy van mon tap
	*/
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

	/*
	Truy van bai tap
	*/
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
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "UPDATE " + TABLE_BAITAP + " SET " + KEY_HOANTHANH + " = 1" +  " where (" + KEY_TENBAITAP + " like '"+ id + "')";

		db.execSQL(sql);
	}

	/*
	Truy van chuong trinh giam can
	*/
	public ChuongTrinhGiamCan getBaiTapChuongTrinhTapTheoId(int id) {
		List<ChuongTrinhGiamCan> list = new ArrayList<ChuongTrinhGiamCan>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select distinct * from " + TABLE_CHUONGTRINHGIAMCAN + " where (" + KEY_IDCHUONGTRINH + " like '"+ id + "')";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
			ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();

		chuongTrinhGiamCan.setId(Integer.parseInt(cursor.getString(0)));
		chuongTrinhGiamCan.setTenChuongTrinh(cursor.getString(1));
		chuongTrinhGiamCan.setNoiDungChuongTrinh(cursor.getString(2));
		chuongTrinhGiamCan.setNgay(cursor.getString(3));
		chuongTrinhGiamCan.setMaMonTap(cursor.getString(4));
		chuongTrinhGiamCan.setCanNangHienTai(Integer.parseInt(cursor.getString(5)));
		chuongTrinhGiamCan.setCanNangMucTieu(Integer.parseInt(cursor.getString(6)));
		chuongTrinhGiamCan.setSoCanNangCatGiam(Integer.parseInt(cursor.getString(7)));
		chuongTrinhGiamCan.setSoGioMoiNgay(Double.parseDouble(cursor.getString(8)));
		chuongTrinhGiamCan.setSoNgayTap(Integer.parseInt(cursor.getString(9)));
		chuongTrinhGiamCan.setTienDo(Integer.parseInt(cursor.getString(10)));
		list.add(chuongTrinhGiamCan);

		return chuongTrinhGiamCan;
	}
	public List<ChuongTrinhGiamCan> getChuongTrinhGiamCan() {
		List<ChuongTrinhGiamCan> list = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from " + TABLE_CHUONGTRINHGIAMCAN ;

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();

			chuongTrinhGiamCan.setId(Integer.parseInt(cursor.getString(0)));
			chuongTrinhGiamCan.setTenChuongTrinh(cursor.getString(1));
			chuongTrinhGiamCan.setNoiDungChuongTrinh(cursor.getString(2));
			chuongTrinhGiamCan.setNgay(cursor.getString(3));
			chuongTrinhGiamCan.setMaMonTap(cursor.getString(4));
			chuongTrinhGiamCan.setCanNangHienTai(Integer.parseInt(cursor.getString(5)));
			chuongTrinhGiamCan.setCanNangMucTieu(Integer.parseInt(cursor.getString(6)));
			chuongTrinhGiamCan.setSoCanNangCatGiam(Integer.parseInt(cursor.getString(7)));
			chuongTrinhGiamCan.setSoGioMoiNgay(Double.parseDouble(cursor.getString(8)));
			chuongTrinhGiamCan.setSoNgayTap(Integer.parseInt(cursor.getString(9)));
			chuongTrinhGiamCan.setTienDo(Integer.parseInt(cursor.getString(10)));
			list.add(chuongTrinhGiamCan);

			cursor.moveToNext();

		}

		return list;
	}
	public void updateChuongTrinhGiamCan(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();
		chuongTrinhGiamCan = getBaiTapChuongTrinhTapTheoId(id);
		int TienDo = chuongTrinhGiamCan.getTienDo() + 1;
		String sql = "UPDATE " + TABLE_CHUONGTRINHGIAMCAN + " SET " + (KEY_TIENDO) + " = " + TienDo + " where (" + KEY_IDCHUONGTRINH + " like '"+ id + "')";

		db.execSQL(sql);
	}
	public void addChuongTrinhGiamCan(ChuongTrinhGiamCan chuongTrinhGiamCan){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_TENCHUONGTRINH, chuongTrinhGiamCan.getTenChuongTrinh());
		values.put(KEY_NOIDUNGCHUONGTRINH, chuongTrinhGiamCan.getNoiDungChuongTrinh());
		values.put(KEY_NGAYTAOCHUONGTRINH, chuongTrinhGiamCan.getNgay());
		values.put(KEY_MAMONTAP, chuongTrinhGiamCan.getMaMonTap());
		values.put(KEY_CANNANGHIENTAI, chuongTrinhGiamCan.getCanNangHienTai());
		values.put(KEY_CANNANGMUCTIEU, chuongTrinhGiamCan.getCanNangMucTieu());
		values.put(KEY_SOCANNANGCATGIAM, chuongTrinhGiamCan.getSoCanNangCatGiam());
		values.put(KEY_SOGIOMOINGAY, chuongTrinhGiamCan.getSoGioMoiNgay());
		values.put(KEY_SONGAYTAP, chuongTrinhGiamCan.getSoNgayTap());
		values.put(KEY_TIENDO, chuongTrinhGiamCan.getTienDo());


		if(db.insert(TABLE_CHUONGTRINHGIAMCAN, null, values)!= -1){
			Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
		}
		db.close();
	}

}



