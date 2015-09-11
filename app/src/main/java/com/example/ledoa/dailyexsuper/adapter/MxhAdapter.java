
package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemMxh;

import java.util.List;


public class MxhAdapter extends ArrayAdapter<ItemMxh> {

	Context context;
	int resource;
	List<ItemMxh> objects;
	public MxhAdapter(Context context, int resource, List<ItemMxh> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, resource, null);
		
		ImageView avatar = (ImageView)view.findViewById(R.id.AvatarMxh);
		ImageView StatusMxh = (ImageView)view.findViewById(R.id.StatusMxh);
		TextView textViewTkhoanMxh = (TextView)view.findViewById(R.id.textViewTkhoanMxh);
		TextView textViewStatusMxh = (TextView)view.findViewById(R.id.textViewStatusMxh);
		
		
		
		ItemMxh item = objects.get(position);
		textViewTkhoanMxh.setText(item.getTkhoan());
		textViewStatusMxh.setText(item.getStatus());
		
		return view;
	}

	
}
