package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemChat;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<ItemChat> {

	Context context;
	int resource;
	List<ItemChat> objects;
	public ChatAdapter(Context context, int resource, List<ItemChat> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, resource, null);
		
		ImageView avatar1 = (ImageView)view.findViewById(R.id.AvartarChat1);
		ImageView avatar2 = (ImageView)view.findViewById(R.id.avartarChat2);
		TextView chat1 = (TextView)view.findViewById(R.id.textViewChat1);
		TextView chat2 = (TextView)view.findViewById(R.id.textViewChat2);
		
		ItemChat item = objects.get(position);
		avatar1.setImageResource(item.getAvatar1());
		avatar2.setImageResource(item.getAvatar2());
		chat1.setText(item.getChat1());
		chat2.setText(item.getChat2());
		return view;
	}

	
}
