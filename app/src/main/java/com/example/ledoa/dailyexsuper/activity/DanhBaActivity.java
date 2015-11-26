package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.widget.ListView;
import android.widget.SearchView;
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
import java.util.Locale;

public class DanhBaActivity extends Activity {

	private ArrayList<User> mUserList = new ArrayList<>();
	ArrayList<User> mStudentListSearching, mStudentList;
	UserAdapter mUserAdapter;
	GetListUserRequest mGetListUserRequest;
	ListView mLvDanhBa;
	DanhBaPref danhBaPref;
	SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_ba);
		TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
		searchView = (SearchView)findViewById(R.id.sv_danh_ba);
		title.setText("Danh bạ");
		attackSearchView();
		getListUsers();
		danhBaPref = new DanhBaPref();
		showDanhBaOffline();

	}

	public void attackSearchView(){
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				filter(newText.toString().toLowerCase(Locale.getDefault()));
				Toast.makeText(DanhBaActivity.this, newText.toString().toLowerCase(Locale.getDefault()), Toast.LENGTH_SHORT).show();
				return true;
			}
		});
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
	public void filter(String charSearching) {
		mStudentListSearching = new ArrayList<>();
		mStudentList = new ArrayList<>();
		mStudentList = (ArrayList<User>) danhBaPref.getListUser();
		charSearching = charSearching.toLowerCase(Locale.getDefault());
		mStudentListSearching.clear();
		if (charSearching.length() == 0) {
			this.mStudentListSearching.addAll(mStudentList);
		} else {
			for (int i = 0; i < mStudentList.size(); i++) {
				if ((mStudentList.get(i)).username.toLowerCase(Locale.getDefault()).contains(charSearching)) {
					mStudentListSearching.add(mStudentList.get(i));
				}
			}
		}
		mUserAdapter = new UserAdapter(getApplicationContext(), mStudentListSearching);
		mUserAdapter.notifyDataSetChanged();
		mLvDanhBa.setAdapter(mUserAdapter);
	}
}