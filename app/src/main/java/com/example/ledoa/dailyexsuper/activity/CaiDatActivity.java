package com.example.ledoa.dailyexsuper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThongBaoAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListThongBaoRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListThongBaoResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;

import java.util.ArrayList;

public class CaiDatActivity extends AppCompatActivity {

    ArrayList<ThongBao> mUserList = new ArrayList<>();
    ThongBaoAdapter mThongBaoAdapter;
    FriendsList friendsList;
    GetListThongBaoRequest mGetListUserRequest;
    ListView mLvThongBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        getSupportActionBar().hide();

        TextView mActionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
        mActionbar_tvTitile.setText("Cài đặt riêng tư");

       getListThongBao();

    }


    private void getListThongBao() {
        mLvThongBao = (ListView)findViewById(R.id.lvCaiDat);

        mGetListUserRequest = new GetListThongBaoRequest(Method.GET, ApiLink.getListThongBao(), null, null) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(ListThongBaoResponse entity, int statusCode, String message) {
                mUserList.clear();
                mUserList.addAll(entity.data);
                String s = entity.data.get(0).user._id.toString();
                friendsList = new FriendsList();
                mThongBaoAdapter = new ThongBaoAdapter(getApplicationContext(), mUserList);
                mThongBaoAdapter.notifyDataSetChanged();
                mLvThongBao.setAdapter(mThongBaoAdapter);
                Toast.makeText(getApplicationContext(), "Get failed with error: " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();


    }
}
