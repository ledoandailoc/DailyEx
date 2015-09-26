
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
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

public class ThongBaoAdapter extends ArrayAdapter<ThongBao> {
	private Context context;
	private ArrayList<ThongBao> mList;
	ThongBao thongbao;
	String notify;
	UserPref mUserPref;
	Socket mSocket;
	public ThongBaoAdapter(Context context, ArrayList<ThongBao> list) {
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
		mUserPref = new UserPref();
		thongbao = mList.get(position);
		if(thongbao.news.title != null){
			if(thongbao.news.title.length() >= 24){
				notify = "Tin mới: " + thongbao.news.title.substring(0,20)+ " ...";
			}
			else
				notify = "Tin mới: " + thongbao.news.title;
		}
		else if (thongbao.friend.senderId != null){

			if(!thongbao.friend.senderId.equals(mUserPref.getUser()._id) ){
				if(thongbao.friend.success == false){
					viewHolder.btThongBao.setVisibility(View.VISIBLE);
					notify ="đã gửi cho bạn yêu cầu kết bạn";
				} else {
					notify = "Bạn đã đồng ý yêu cầu kết bạn.";
				}
			} else {

				if(thongbao.friend.success == false){

					notify ="đã gửi yêu cầu kết bạn thành công";
				} else {
					notify = "đã được chập nhận";

				}
			}
		}

		viewHolder.tvThongbao.setText(notify);
		viewHolder.tvUsername.setText(mList.get(position).user.username);
		if (mList.get(position).user.avatar != null) {
			ImageLoaderUtil.display(mList.get(position).user.avatar, viewHolder.ivUseravatar);
		} else {
			viewHolder.ivUseravatar.setImageResource(R.drawable.avt);
		}

		viewHolder.btThongBao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSocket = MainApplication.getMySocket().getSocket();
				mSocket.emit("AcceptFriend", mList.get(position).friend._id);
				viewHolder.btThongBao.setVisibility(View.INVISIBLE);
				Toast.makeText(context, "Đã chấp nhận lời mời kết bạn.", Toast.LENGTH_SHORT).show();
			}
		});

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
