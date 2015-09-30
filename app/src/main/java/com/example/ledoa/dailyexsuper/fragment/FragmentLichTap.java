package com.example.ledoa.dailyexsuper.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.ConvertDate;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;
import com.example.ledoa.dailyexsuper.util.ScaleImage;
import com.example.ledoa.dailyexsuper.activity.LichtapDetailActivity;

import java.util.Calendar;
import java.util.Date;



public class FragmentLichTap extends Fragment implements DatePicker.OnDateChangedListener{
	
	DatabaseHandle databaseHandle;

	DatePicker datePicker;
	ImageButton imageButtonChiTiet;
	TextView textViewHienThi;
	TextView textViewMonTap;
	TextView textViewTongThoiGian;
	CalendarView calendarView;
	
	int iDay ;
	int iMonth ;
	int iYear ;
	
	Date date;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_lichtap, container,false);

		databaseHandle = new DatabaseHandle(getActivity());

		imageButtonChiTiet = (ImageButton) view.findViewById(R.id.imageButton_chitiet);
		/*calendarView = (CalendarView) view.findViewById(R.id.ca);*/
		datePicker = (DatePicker) view.findViewById(R.id.datePicker);
		textViewHienThi = (TextView) view.findViewById(R.id.textView_ngay);
		textViewMonTap = (TextView) view.findViewById(R.id.TextView_montap);
		textViewTongThoiGian = (TextView) view.findViewById(R.id.textView_tongthoigian);
		
		ScaleImage.SetImage(getResources(), imageButtonChiTiet, R.drawable.icon_chitietlichtap1, 200, 200);
		
		Calendar c = Calendar.getInstance();
		iYear = c.get(Calendar.YEAR);
		iMonth = c.get(Calendar.MONTH);
		iDay = c.get(Calendar.DAY_OF_MONTH);
		
		/*calendarView.*/
		date = new Date();
		date.setDate(iDay);
		date.setMonth(iMonth);
		date.setYear(iYear - 1900);
		
		Date nowDate = new Date();
		
		textViewHienThi.setText(String.valueOf(ConvertDate.ConvertNgayToString(nowDate)));
		int tongThoigian = databaseHandle.tinhTongThoiGianTapTrongNgay(ConvertDate.ConvertNgayToString(nowDate));
		textViewMonTap.setText("Môn tập: " + databaseHandle.getTenMonTapTheoNgay(ConvertDate.ConvertNgayToString(nowDate)));
		textViewTongThoiGian.setText("Tổng thời gian tập luyện: " + String.valueOf(DoiGioPhutGiay.GiaySangGio(Long.parseLong(String.valueOf(tongThoigian)))));
		
		imageButtonChiTiet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LichtapDetailActivity.class);
				intent.putExtra("Date", String.valueOf(ConvertDate.ConvertNgayToString(date)));
				startActivity(intent);
			
			}
		});

		datePicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

				date = new Date();
				date.setDate(dayOfMonth);
				date.setMonth(month);
				date.setYear(year - 1900);

				textViewHienThi.setText(String.valueOf(ConvertDate.ConvertNgayToString(date)));

				int tongThoigian = databaseHandle.tinhTongThoiGianTapTrongNgay(ConvertDate.ConvertNgayToString(date));
				textViewMonTap.setText("Môn tập: " + databaseHandle.getTenMonTapTheoNgay(ConvertDate.ConvertNgayToString(date)));
				textViewTongThoiGian.setText("Tổng thời gian tập luyện: " + String.valueOf(DoiGioPhutGiay.GiaySangGio(Long.parseLong(String.valueOf(tongThoigian)))));

			}
		});


		return view;
	}
	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		date = new Date();
		date.setDate(dayOfMonth);
		date.setMonth(dayOfMonth);
		date.setYear(year - 1900);

		textViewHienThi.setText(String.valueOf(ConvertDate.ConvertNgayToString(date)));

		int tongThoigian = databaseHandle.tinhTongThoiGianTapTrongNgay(ConvertDate.ConvertNgayToString(date));
		textViewMonTap.setText("Môn tập: " + databaseHandle.getTenMonTapTheoNgay(ConvertDate.ConvertNgayToString(date)));
		textViewTongThoiGian.setText("Tổng thời gian tập luyện: " + String.valueOf(DoiGioPhutGiay.GiaySangGio(Long.parseLong(String.valueOf(tongThoigian)))));

	}
}
	
