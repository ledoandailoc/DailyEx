package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.UserAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListUserResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.example.ledoa.dailyexsuper.util.DanhBaPref;

import java.util.ArrayList;

public class DanhBaActivity extends Activity {

	private ArrayList<User> mUserList = new ArrayList<>();
	UserAdapter mUserAdapter;
	GetListUserRequest mGetListUserRequest;
	ListView mLvDanhBa;
	DanhBaPref danhBaPref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_ba);
		TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
		title.setText("Danh bạ");
		getListUsers();
		danhBaPref = new DanhBaPref();
		showDanhBaOffline();

	}

	public void showDanhBaOffline(){
		if(danhBaPref.getListUser()!=null){
			mLvDanhBa = (ListView)findViewById(R.id.lvDanhBa);
			mUserList.addAll(danhBaPref.getListUser());
			mUserAdapter = new UserAdapter(getApplicationContext(), mUserList);
			mUserAdapter.notifyDataSetChanged();
			mLvDanhBa.setAdapter(mUserAdapter);
		}
	}

	private void getListUsers() {
		mLvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
		mGetListUserRequest = new GetListUserRequest(Method.GET, ApiLink.getContactLink(), null, null) {
			@Override
			protected void onStart() {
			}

			@Override
			protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
				mUserList.clear();
				mUserList.addAll(entity.data);
				danhBaPref.setListUser(entity.data);
				mUserAdapter = new UserAdapter(getApplicationContext(), mUserList);
				mUserAdapter.notifyDataSetChanged();
				mLvDanhBa.setAdapter(mUserAdapter);
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();

	}
}