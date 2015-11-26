package com.example.ledoa.dailyexsuper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;
import com.example.ledoa.dailyexsuper.sqlite.DTO.DinhDuong;
import com.example.ledoa.dailyexsuper.sqlite.DTO.DonVi;
import com.example.ledoa.dailyexsuper.sqlite.DTO.LanTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper {

	SQLiteDatabase myDataBase;
	String DATABASE_PATH = "/data/data/com.example.ledoa.dailyexsuper/databases/";

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


	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/*
	Truy van mon an
	 */
	public List<MonAn> getMonAn() {
		List<MonAn> list = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from Foods";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			MonAn monAn = new MonAn();

			monAn.setId(Integer.parseInt(cursor.getString(0)));
			monAn.setCategoryId(Integer.parseInt(cursor.getString(1)));
			monAn.setName(cursor.getString(2));
			list.add(monAn);

			cursor.moveToNext();

		}

		return list;
	}

	public MonAn getMonAnTheoId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from Foods where id = " + id +"";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();

			MonAn monAn = new MonAn();
			monAn.setId(Integer.parseInt(cursor.getString(0)));
			monAn.setCategoryId(Integer.parseInt(cursor.getString(1)));
			monAn.setName(cursor.getString(2));

		return monAn;
	}

	public ArrayList<DonVi> getDonVi(int food_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<DonVi> list = new ArrayList<>();
		String sql = "select measure_id from Food_Measure where food_id = '" + food_id +"'";

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {

			String sql1 = "select * from Measures where id = '" + Integer.parseInt(cursor.getString(0)) +"'";
			Cursor cursor1 = db.rawQuery(sql1, null);
			cursor1.moveToFirst();

			DonVi donVi= new DonVi();
			donVi.setId(Integer.parseInt(cursor1.getString(0)));
			donVi.setName(cursor1.getString(1).toString());

			list.add(donVi);

			cursor.moveToNext();

		}

		return list;
	}

	public DinhDuong getDinhDuong(int food_id, int measure_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from Food_Measure where food_id = " + food_id +" and measure_id = " + measure_id;

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();

		DinhDuong dinhDuong = new DinhDuong();
		dinhDuong.setId(Integer.parseInt(cursor.getString(0)));
		dinhDuong.setCalories(Integer.parseInt(cursor.getString(4)));
		dinhDuong.setProtein(Double.parseDouble(cursor.getString(12)));
		dinhDuong.setCarbohydrates(Double.parseDouble(cursor.getString(9)));
		dinhDuong.setFat(Double.parseDouble(cursor.getString(5)));
		dinhDuong.setFiber(Double.parseDouble(cursor.getString(10)));
		dinhDuong.setSugars(Double.parseDouble(cursor.getString(11)));
		dinhDuong.setCholesterol(Double.parseDouble(cursor.getString(7)));


		return dinhDuong;
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

	/**
	 * This method will create database in application package /databases
	 * directory when first time application launched
	 **/
	public void createDataBase() throws IOException {
		boolean mDataBaseExist = checkDataBase();
		if (!mDataBaseExist) {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException mIOException) {
				mIOException.printStackTrace();
				throw new Error("Error copying database");
			} finally {
				this.close();
			}
		}
	}

	/** This method checks whether database is exists or not **/
	private boolean checkDataBase() {
		try {
			final String mPath = DATABASE_PATH + DATABASE_NAME;
			final File file = new File(mPath);
			if (file.exists())
				return true;
			else
				return false;
		} catch (SQLiteException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method will copy database from /assets directory to application
	 * package /databases directory
	 **/
	private void copyDataBase() throws IOException {
		try {

			InputStream mInputStream = context.getAssets().open(DATABASE_NAME);
			String outFileName = DATABASE_PATH + DATABASE_NAME;
			OutputStream mOutputStream = new FileOutputStream(outFileName);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = mInputStream.read(buffer)) > 0) {
				mOutputStream.write(buffer, 0, length);
			}
			mOutputStream.flush();
			mOutputStream.close();
			mInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This method open database for operations **/
	public boolean openDataBase() throws SQLException {
		String mPath = DATABASE_PATH + DATABASE_NAME;
		 myDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		return myDataBase.isOpen();
	}

	/** This method close database connection and released occupied memory **/
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		SQLiteDatabase.releaseMemory();
		super.close();
	}

}



