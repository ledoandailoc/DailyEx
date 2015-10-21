package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;

import java.util.List;

public class DanhSachMonAnAdapter extends ArrayAdapter<MonAn> {
	Context context;
	int resource;
	List<MonAn> list;

	TextView tvTenMonAn;

	public DanhSachMonAnAdapter(Context context, int resource, List<MonAn> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		tvTenMonAn = (TextView) view.findViewById(R.id.tv_tenmonan);


		MonAn monAn= list.get(vitri);

		tvTenMonAn.setText(String.valueOf(monAn.getName()));

		return view;
	}

}
