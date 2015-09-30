package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.activity.BaiTapDiBoActivity;
import com.example.ledoa.dailyexsuper.activity.BaiTapGiamCanActivity;
import com.example.ledoa.dailyexsuper.activity.ChuongTrinhTangSucBenActivity;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;


public class TranningPlanAdapter extends ArrayAdapter<String> {
	Context context;
	int resource;
	String argMonTap[];
	int argImgMonTap[];
	Button button;

	public TranningPlanAdapter(Context context, int resource, String objects[], int objects2[]) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.argMonTap = objects;
		this.argImgMonTap = objects2;
	}


	@Override
	public View getView(int vitri, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageView_training);
		RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rl_tranning);
		Button button = (Button) view.findViewById(R.id.button_training);

		imageView.setImageResource(argImgMonTap[vitri]);
		button.setText(String.valueOf(argMonTap[vitri]));

		switch (vitri){
			case 0:
				rl.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent  intent = new Intent(context, BaiTapDiBoActivity.class);
						getContext().startActivity(intent);
					}
				});
				break;
			case 1:
				rl.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent  intent = new Intent(context, ChuongTrinhTangSucBenActivity.class);
						getContext().startActivity(intent);
					}
				});
				break;
			case 2:
				rl.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent  intent = new Intent(context, BaiTapGiamCanActivity.class);
						getContext().startActivity(intent);
					}
				});
				break;
		};


		return view;
	}

}
