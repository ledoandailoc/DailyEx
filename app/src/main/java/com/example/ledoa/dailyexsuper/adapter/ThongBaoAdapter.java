
package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemThongBao;

import java.util.List;


public class ThongBaoAdapter extends ArrayAdapter<ItemThongBao> {

	Context context;
	int resource;
	List<ItemThongBao> objects;
	public ThongBaoAdapter(Context context, int resource, List<ItemThongBao> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, resource, null);
		
		ImageView avatar = (ImageView)view.findViewById(R.id.AvatarThongBao);
		TextView text = (TextView)view.findViewById(R.id.textViewThongBao);
		
		ItemThongBao item = objects.get(position);
		avatar.setImageResource(item.getAvatar());
		text.setText(item.getText());
		
		return view;
	}

	
}
