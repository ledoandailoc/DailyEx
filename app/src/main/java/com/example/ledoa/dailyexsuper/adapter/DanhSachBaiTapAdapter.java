package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.LanTap;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay2;

import java.util.List;

public class DanhSachBaiTapAdapter extends ArrayAdapter<BaiTap> {
	Context context;
	int resource;
	List<BaiTap> list;


	TextView tvTenBaiTap, tvMucTieu;
	ImageView ivIconChecked;

	public DanhSachBaiTapAdapter(Context context, int resource, List<BaiTap> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		tvTenBaiTap = (TextView) view.findViewById(R.id.tv_tenbaitap);
		tvMucTieu = (TextView) view.findViewById(R.id.tv_muctieu);
		ivIconChecked = (ImageView) view.findViewById(R.id.iv_checked);

		BaiTap baiTap = new BaiTap();
		baiTap = list.get(vitri);

		if (baiTap.getLoaiMucTieu().equals("TG")) {
			String time = DoiGioPhutGiay2.GiaySangGio(baiTap.getMucTieu());
			tvMucTieu.setText(String.valueOf(time));
		}
		else {
			tvMucTieu.setText(String.valueOf(baiTap.getMucTieu()));
		}

		tvTenBaiTap.setText(String.valueOf(baiTap.getTenBaiTap()));

		if (baiTap.getHoanThanh() == 1){
			tvMucTieu.setTextColor(context.getResources().getColor(R.color.item_finish));
			tvTenBaiTap.setTextColor(context.getResources().getColor(R.color.item_finish));
			ivIconChecked.setVisibility(View.VISIBLE);
		}
		return view;
	}

}
