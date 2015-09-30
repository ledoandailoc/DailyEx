package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThemBanAdapter;
import com.example.ledoa.dailyexsuper.adapter.UserAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListUserResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

public class ThemBanActivity extends Activity {

	private ArrayList<User> mUserList = new ArrayList<>();
	ThemBanAdapter mThemBanAdapter;
	GetListUserRequest mGetListUserRequest;
	ListView mLvThemBan;
	Socket mSocket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_ban);
		TextView title  = (TextView)findViewById(R.id.actionbar_tvTitile);
		title.setText("Thêm bạn");

		getListUsers();
	}

	private void getListUsers() {
		mLvThemBan = (ListView)findViewById(R.id.lvThemBan);

		mGetListUserRequest = new GetListUserRequest(Method.GET, ApiLink.getAllUserLink(), null, null) {
			@Override
			protected void onStart() {
			}

			@Override
			protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
				mUserList.clear();
				mUserList.addAll(entity.data);
				mThemBanAdapter = new ThemBanAdapter(getApplicationContext(), mUserList);
				mThemBanAdapter.notifyDataSetChanged();
				mLvThemBan.setAdapter(mThemBanAdapter);
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();

	}

}
