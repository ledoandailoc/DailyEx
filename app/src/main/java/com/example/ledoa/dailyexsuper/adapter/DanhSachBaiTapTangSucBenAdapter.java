package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.activity.ChuongTrinhTangSucBenActivity;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;

import java.util.List;

public class DanhSachBaiTapTangSucBenAdapter extends ArrayAdapter<ChuongTrinhTangSucBen> {
	Context context;
	int resource;
	List<ChuongTrinhTangSucBen> list;

	TextView tvNgay, tvTenChuongTrinh, tvNoiDungChuongTrinh;
	ImageView ivHoanThanh, ivTangSucBen;

	public DanhSachBaiTapTangSucBenAdapter(Context context, int resource, List<ChuongTrinhTangSucBen> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		tvNgay = (TextView) view.findViewById(R.id.tv_ngay);
		tvTenChuongTrinh = (TextView) view.findViewById(R.id.tv_tenchuongtrinh);
		tvNoiDungChuongTrinh = (TextView) view.findViewById(R.id.tv_noidungchuongtrinh);
		ivHoanThanh = (ImageView) view.findViewById(R.id.tv_hoanthanh);
		ivTangSucBen = (ImageView) view.findViewById(R.id.iv_icon_tangsucben);

		ChuongTrinhTangSucBen chuongTrinhTangSucBen= list.get(vitri);

		tvNgay.setText("Ngày " + String.valueOf(chuongTrinhTangSucBen.getId()));
		tvTenChuongTrinh.setText(String.valueOf(chuongTrinhTangSucBen.getTenChuongTrinh()));
		tvNoiDungChuongTrinh.setText(String.valueOf(chuongTrinhTangSucBen.getNoiDung()));

		if (chuongTrinhTangSucBen.getHoanThanh() == 1){
			tvNgay.setTextColor(context.getResources().getColor(R.color.item_finish));
			tvTenChuongTrinh.setTextColor(context.getResources().getColor(R.color.item_finish));
			tvNoiDungChuongTrinh.setTextColor(context.getResources().getColor(R.color.item_finish));
			ivHoanThanh.setImageResource(R.drawable.icon_checked);
			ivTangSucBen.setImageResource(R.drawable.icon_sucben_finish);
		}
		if (chuongTrinhTangSucBen.getTenChuongTrinh().equals("Nghỉ ngơi")){
			ivHoanThanh.setVisibility(View.INVISIBLE);
		}

		return view;
	}

}
