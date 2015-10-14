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
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListThongBaoRequest;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListThongBaoResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;

public class ThongBaoActivity extends Activity {
	ArrayList<ThongBao> mUserList = new ArrayList<>();
	ThongBaoAdapter mThongBaoAdapter;
	GetListThongBaoRequest mGetListUserRequest;
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

		mGetListUserRequest = new GetListThongBaoRequest(Method.GET, ApiLink.getListThongBao(), null, null) {
			@Override
			protected void onStart() {
			}

			@Override
			protected void onSuccess(ListThongBaoResponse entity, int statusCode, String message) {
				mUserList.clear();
				mUserList.addAll(entity.data);

				mThongBaoAdapter = new ThongBaoAdapter(getApplicationContext(), mUserList);
				mThongBaoAdapter.notifyDataSetChanged();
				mLvThongBao.setAdapter(mThongBaoAdapter);
				Toast.makeText(getApplicationContext(), "Đã nhận thông báo", Toast.LENGTH_SHORT).show();
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();


	}

}

