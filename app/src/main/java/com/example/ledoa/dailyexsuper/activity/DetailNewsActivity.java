package com.example.ledoa.dailyexsuper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class DetailNewsActivity extends AppCompatActivity {

    ArrayList<News> mUserList = new ArrayList<>();
    MangXaHoiAdapter mThongBaoAdapter;
    GetListNewsRequest mGetListUserRequest;
    ListView mLvThongBao;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        String title = getIntent().getExtras().getString("title");
        String content = getIntent().getExtras().getString("content");
        String desciption = getIntent().getExtras().getString("desciption");
        String thumbnail = getIntent().getExtras().getString("thumbnail");


            Toast.makeText(this, "xx"+title + content + desciption + thumbnail, Toast.LENGTH_LONG).show();



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
                mThongBaoAdapter = new MangXaHoiAdapter(getApplication(), mUserList);
                mThongBaoAdapter.notifyDataSetChanged();
                mLvThongBao.setAdapter(mThongBaoAdapter);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getApplication(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();
    }
}
