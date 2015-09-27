
package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;
import com.example.ledoa.dailyexsuper.customview.CircleImageView;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Comment;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {
	private Context context;
	private ArrayList<Comment> mList;
	Comment thongbao;
	UserPref mUserPref;
	public CommentAdapter(Context context, ArrayList<Comment> list) {
		super(context, 0, list);
		this.context = context;
		this.mList = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.custom_layout_thongbao, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvThongbao.setText(mList.get(position).content);
		viewHolder.tvUsername.setText(mList.get(position).user.username);
		if (mList.get(position).user.avatar != null) {
			ImageLoaderUtil.display(mList.get(position).user.avatar, viewHolder.ivUseravatar);
		} else {
			viewHolder.ivUseravatar.setImageResource(R.drawable.avt);
		}

		return convertView;
	}

	public class ViewHolder {

		public TextView tvThongbao;
		public TextView tvUsername;
		public CircleImageView ivUseravatar;
		public Button btThongBao;

		public ViewHolder(View rootView) {
			tvThongbao = (TextView) rootView.findViewById(R.id.tv_thongbao);
			tvUsername = (TextView) rootView.findViewById(R.id.tv_username);
			ivUseravatar = (CircleImageView) rootView.findViewById(R.id.iv_avatar_user);
			btThongBao = (Button)rootView.findViewById(R.id.bt_thongbao);
			btThongBao.setVisibility(View.INVISIBLE);
		}
	}

}
