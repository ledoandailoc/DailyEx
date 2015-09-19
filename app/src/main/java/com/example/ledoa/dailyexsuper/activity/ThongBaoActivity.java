package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThongBaoAdapter;
import com.example.ledoa.dailyexsuper.adapter.UserAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListUserResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import java.util.ArrayList;

public class ThongBaoActivity extends Activity {
	ArrayList<User> mUserList = new ArrayList<>();
	ThongBaoAdapter mThongBaoAdapter;
	FriendsList friendsList;
	GetListUserRequest mGetListUserRequest;
	ListView mLvThongBao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_bao);

		TextView actionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
		actionbar_tvTitile.setText("Thông báo");

		getListThongBao();
	}

	private void getListThongBao() {
		mLvThongBao = (ListView)findViewById(R.id.lvThongBao);

		mGetListUserRequest = new GetListUserRequest(Method.GET, ApiLink.getContactLink(), null, null) {
			@Override
			protected void onStart() {
			}

			@Override
			protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
				mUserList.clear();
				mUserList.addAll(entity.data);
				friendsList = new FriendsList();
				mThongBaoAdapter = new ThongBaoAdapter(getApplicationContext(), mUserList);
				mThongBaoAdapter.notifyDataSetChanged();
				mLvThongBao.setAdapter(mThongBaoAdapter);
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();

		mLvThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent iMessage = new Intent(ThongBaoActivity.this, ChatActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("UserId", mUserList.get(position)._id);
				iMessage.putExtras(mBundle);
				startActivity(iMessage);
			}
		});

	}

}

