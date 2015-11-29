package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.os.Bundle;
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

public class ThongTinCaNhanActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_ca_nhan);
	}


}