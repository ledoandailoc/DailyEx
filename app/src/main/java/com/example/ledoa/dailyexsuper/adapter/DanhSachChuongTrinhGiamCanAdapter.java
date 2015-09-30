package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay2;

import java.util.List;

public class DanhSachChuongTrinhGiamCanAdapter extends ArrayAdapter<ChuongTrinhGiamCan> {
	Context context;
	int resource;
	List<ChuongTrinhGiamCan> list;


	TextView tvTenChuongTrinh, tvNgayTao, tvNoiDung, tvTienDo1, tvTienDo, tvCanNangMucTieu;
	ImageView ivIconChecked;

	public DanhSachChuongTrinhGiamCanAdapter(Context context, int resource, List<ChuongTrinhGiamCan> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}
	
	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		tvTenChuongTrinh = (TextView) view.findViewById(R.id.tv_tenchuongtrinh);
		tvNgayTao = (TextView) view.findViewById(R.id.tv_ngaytao);
		tvNoiDung = (TextView) view.findViewById(R.id.tv_noidungchuongtrinh);
		tvTienDo = (TextView) view.findViewById(R.id.tv_tiendo);
		tvTienDo1 = (TextView) view.findViewById(R.id.tv_tiendo1);
		tvCanNangMucTieu = (TextView) view.findViewById(R.id.tv_cannangmuctieu);
		ivIconChecked = (ImageView) view.findViewById(R.id.iv_checked);

		ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();
		chuongTrinhGiamCan = list.get(vitri);

		tvTenChuongTrinh.setText(chuongTrinhGiamCan.getTenChuongTrinh());
		tvNgayTao.setText("Ngày tạo: " + chuongTrinhGiamCan.getNgay());
		tvNoiDung.setText("Nội dung: " + chuongTrinhGiamCan.getNoiDungChuongTrinh());
		tvTienDo.setText(String.valueOf(chuongTrinhGiamCan.getTienDo() + "/" + chuongTrinhGiamCan.getSoNgayTap() + "ngày"));
		tvCanNangMucTieu.setText(String.valueOf(chuongTrinhGiamCan.getCanNangHienTai() + "kg -> " + chuongTrinhGiamCan.getCanNangMucTieu() + "kg"));

		if (chuongTrinhGiamCan.getTienDo() == chuongTrinhGiamCan.getSoNgayTap()){
			ivIconChecked.setImageResource(R.drawable.icon_checked);
			tvTienDo.setTextColor(context.getResources().getColor(R.color.item_finish));
			tvTienDo1.setTextColor(context.getResources().getColor(R.color.item_finish));
			ivIconChecked.setVisibility(View.VISIBLE);
		}

		return view;
	}

}
