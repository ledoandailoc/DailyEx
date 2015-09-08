package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemMenuLeft;

import java.util.List;


public class LeftAdapter extends ArrayAdapter<ItemMenuLeft> {
    Context context;
    int resID;
    List<ItemMenuLeft> objects;
    public LeftAdapter(Context context, int resource, List<ItemMenuLeft> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, resID, null);
        ImageView hinhanh = (ImageView) view.findViewById(R.id.imageView);
        TextView noidung = (TextView) view.findViewById(R.id.contentDrawer);

        ItemMenuLeft item = objects.get(position);

        hinhanh.setImageResource(item.getHinhAnh());
        noidung.setText(item.getTenMenu());
        return view;
    }
}
