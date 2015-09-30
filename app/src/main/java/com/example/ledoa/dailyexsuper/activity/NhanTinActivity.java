package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.UserAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListUserResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import java.util.ArrayList;

public class NhanTinActivity extends AppCompatActivity {
    ArrayList<User> mUserList = new ArrayList<>();
    UserAdapter mUserAdapter;
    FriendsList friendsList;
    GetListUserRequest mGetListUserRequest;
    ListView mLvNhanTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_tin);
        getSupportActionBar().hide();

        TextView actionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
        actionbar_tvTitile.setText("Trạng thái bạn bè");

        getListUsers();
    }

    private void getListUsers() {
        mLvNhanTin = (ListView)findViewById(R.id.lvNhanTin);

        mGetListUserRequest = new GetListUserRequest(Method.GET, ApiLink.getContactLink(), null, null) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
                mUserList.clear();
                mUserList.addAll(entity.data);
                friendsList = new FriendsList();
                mUserAdapter = new UserAdapter(getApplicationContext(), mUserList);
                mUserAdapter.notifyDataSetChanged();
                mLvNhanTin.setAdapter(mUserAdapter);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();

        mLvNhanTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iMessage = new Intent(NhanTinActivity.this, ChatActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("UserId", mUserList.get(position)._id);
                iMessage.putExtras(mBundle);
                startActivity(iMessage);
            }
        });

    }

}
