
package com.example.ledoa.dailyexsuper.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.activity.CommentActivity;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;

import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListNewsRequest;
import com.example.ledoa.dailyexsuper.connection.request.IsLikeRequest;
import com.example.ledoa.dailyexsuper.connection.response.LikesResponse;
import com.example.ledoa.dailyexsuper.connection.response.ListNewsResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.News;
import com.example.ledoa.dailyexsuper.sqlite.DTO.PublicData;
import com.example.ledoa.dailyexsuper.util.UserPref;

import java.util.ArrayList;
import java.util.HashMap;


public class MangXaHoiAdapter extends ArrayAdapter<News> {

		private Context context;
		private ArrayList<News> mList;
		private com.example.ledoa.dailyexsuper.sqlite.DTO.Statistic like_comment;
		private IsLikeRequest mIsLikeRequest;


		public MangXaHoiAdapter(Context context, ArrayList<News> list) {
			super(context, 0, list);
			this.context = context;
			this.mList = list;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.custom_layout_mxh, parent, false);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.tv_thoigian.setText(mList.get(position).createdAt);
			viewHolder.tv_title.setText(mList.get(position).title);
			viewHolder.tv_description.setText(mList.get(position).description);
			like_comment = mList.get(position).statistic;
			viewHolder.tv_like_comment.setText(like_comment.likes + " likes" + " " + like_comment.comments + " comments");

			if (mList.get(position).thumbnail != null) {
				ImageLoaderUtil.display(mList.get(position).thumbnail, viewHolder.iv_thumbnail);
			}

			viewHolder.iv_like.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getIsLike(mList.get(position)._id);
				}
			});

			viewHolder.iv_comment.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent iComment = new Intent(context, CommentActivity.class);
					Bundle mBundle = new Bundle();
					mBundle.putString("newsId", mList.get(position)._id);
					mBundle.putInt("likes", mList.get(position).statistic.likes);
					iComment.putExtras(mBundle);
					context.startActivity(iComment);
				}
			});
			return convertView;
		}

		public class ViewHolder {

			public TextView tv_title, tv_thoigian, tv_like_comment, tv_description;
			public ImageView iv_avatar, iv_thumbnail, iv_like, iv_comment;

			public ViewHolder(View rootView) {
				tv_title = (TextView)rootView.findViewById(R.id.tv_title);
				tv_description = (TextView)rootView.findViewById(R.id.tv_description);
				tv_thoigian = (TextView)rootView.findViewById(R.id.tv_thoigian);
				tv_like_comment = (TextView)rootView.findViewById(R.id.tv_like_comment);
				iv_avatar = (ImageView)rootView.findViewById(R.id.iv_avatar_user);
				iv_thumbnail = (ImageView)rootView.findViewById(R.id.iv_thumbnail);
				iv_like = (ImageView)rootView.findViewById(R.id.iv_like);
				iv_comment = (ImageView)rootView.findViewById(R.id.iv_comment);
			}
		}

		public void getIsLike(String _id){
			HashMap<String, String> params = new HashMap<>();
			params.put("type", "News");
			String url = ApiLink.isLike() + "/" + _id;
			mIsLikeRequest = new IsLikeRequest(Method.POST, url, null, params) {
				@Override
				protected void onStart() {
				}

				@Override
				protected void onSuccess(LikesResponse entity, int statusCode, String message) {
					if(true) {
						Toast.makeText(getContext(), "đã thích", Toast.LENGTH_SHORT).show();
					}	else {
						Toast.makeText(getContext(), "bỏ thích", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				protected void onError(int statusCode, String message) {
					Toast.makeText(getContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
				}
			};
			mIsLikeRequest.execute();
		}

	}
