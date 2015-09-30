package com.example.ledoa.dailyexsuper.activity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.adapter.DanhSachLanAdapter;

import java.util.ArrayList;
import java.util.List;


public class LichtapDetailActivity extends AppCompatActivity {

	ActionBar actionBar;
	DatabaseHandle databaseHandle;
	List list;
	ListView listView;
	Spinner spinner_monTap;
	TextView textView_ngayDetail;
	String date;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lichtap_detail);

		TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
		title.setText("Chi Tiết Ngày Tập");

		getSupportActionBar().hide();
		String arrMaMonTap[] = {"Tất cả môn tập", "Đi bộ", "Chạy bộ", "Đạp xe", "Hít Đất"};

		Bundle bundle = getIntent().getExtras();
		date = "087gyg";
		if (bundle != null)
		{
			date = bundle.getString("Date");
		}

		databaseHandle = new DatabaseHandle(this);

			listView = (ListView)findViewById(R.id.listView1);
			spinner_monTap = (Spinner)findViewById(R.id.spinner_monTap);
			textView_ngayDetail = (TextView)findViewById(R.id.textView_ngayDetail);
			textView_ngayDetail.setText("Ngày: " + date);
			ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrMaMonTap);
			arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_monTap.setAdapter(arrayadapter);
			spinner_monTap.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
					list = new ArrayList();
					switch (i) {
						case 0:
							list = databaseHandle.getLanTapTheoNgay(date);
							break;
						case 1:
							list = databaseHandle.getLanTapTheoNgayMon(date, "DB");
							break;
						case 2:
							list = databaseHandle.getLanTapTheoNgayMon(date, "CB");
							break;
						case 3:
							list = databaseHandle.getLanTapTheoNgayMon(date, "DX");
							break;
						case 4:
							list = databaseHandle.getLanTapTheoNgayMon(date, "HD");
							break;
					}
					DanhSachLanAdapter adapter = new DanhSachLanAdapter(getBaseContext(), R.layout.custom_danhsachmontap, list);
					listView.setAdapter(adapter);
				}

				public void onNothingSelected(AdapterView adapterview) {
				}
			});
		RelativeLayout ivBack = (RelativeLayout) findViewById(R.id.iv_back);
		ivBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});}





}
