package com.example.ledoa.dailyexsuper.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.MangXaHoiAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListNewsRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListNewsResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.News;

import java.util.ArrayList;


public class FragmentMangXaHoi extends Fragment {

	ArrayList<News> mUserList = new ArrayList<>();
	MangXaHoiAdapter mThongBaoAdapter;
	GetListNewsRequest mGetListUserRequest;
	ListView mLvThongBao;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.layout_fragment5, container,false);
		mLvThongBao = (ListView)view.findViewById(R.id.listViewMxh);

		getNews();

		return view;


	}

	public void getNews(){
		mGetListUserRequest = new GetListNewsRequest(Method.GET, ApiLink.getListNews(), null, null) {
			@Override
			protected void onStart() {
			}

			@Override
			protected void onSuccess(ListNewsResponse entity, int statusCode, String message) {
				mUserList.clear();
				mUserList.addAll(entity.data);
				mThongBaoAdapter = new MangXaHoiAdapter(getActivity(), mUserList);
				mThongBaoAdapter.notifyDataSetChanged();
				mLvThongBao.setAdapter(mThongBaoAdapter);
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getActivity(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();
	}


}


