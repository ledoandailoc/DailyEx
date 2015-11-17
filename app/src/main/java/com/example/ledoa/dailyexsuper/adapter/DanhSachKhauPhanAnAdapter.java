package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAnPref;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKhauPhanAnAdapter extends ArrayAdapter<MonAnPref> {
	Context context;
	int resource;
	List<MonAnPref> list;

	TextView tvTenMonAn, tvCalo;

	public DanhSachKhauPhanAnAdapter(Context context, int resource, List<MonAnPref> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);
		tvTenMonAn = (TextView) view.findViewById(R.id.tv_tenmonan);

		MonAnPref monAnPref = list.get(vitri);
		tvTenMonAn.setText(monAnPref.soLuong + " " + monAnPref.donVi + " " + monAnPref.name + " - " + String.valueOf(monAnPref.calo + " calo"));
		return view;
	}

}
