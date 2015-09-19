
package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;
import com.example.ledoa.dailyexsuper.customview.CircleImageView;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import java.util.ArrayList;

public class ThongBaoAdapter extends ArrayAdapter<User> {
	private Context context;
	private ArrayList<User> mList;

	public ThongBaoAdapter(Context context, ArrayList<User> list) {
		super(context, 0, list);
		this.context = context;
		this.mList = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_user, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvId.setText("ID Number: " + mList.get(position)._id);
		viewHolder.tvUsername.setText(mList.get(position).username);
		if (mList.get(position).avatar != null) {
			ImageLoaderUtil.display(mList.get(position).avatar, viewHolder.ivUseravatar);
		} else {
			viewHolder.ivUseravatar.setImageResource(R.drawable.avt);
		}


		return convertView;
	}

	public class ViewHolder {

		public TextView tvId;
		public TextView tvUsername;
		public CircleImageView ivUseravatar;

		public ViewHolder(View rootView) {
			tvId = (TextView) rootView.findViewById(R.id.tv_id);
			tvUsername = (TextView) rootView.findViewById(R.id.tv_username);
			ivUseravatar = (CircleImageView) rootView.findViewById(R.id.iv_avatar_user);
		}
	}

}
