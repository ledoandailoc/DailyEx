package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.CommentAdapter;
import com.example.ledoa.dailyexsuper.adapter.ThongBaoAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListCommentRequest;
import com.example.ledoa.dailyexsuper.connection.request.GetListThongBaoRequest;
import com.example.ledoa.dailyexsuper.connection.request.IsLikeRequest;
import com.example.ledoa.dailyexsuper.connection.response.LikesResponse;
import com.example.ledoa.dailyexsuper.connection.response.ListCommentResponse;
import com.example.ledoa.dailyexsuper.connection.response.ListThongBaoResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Comment;
import com.example.ledoa.dailyexsuper.sqlite.DTO.FriendsList;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentActivity extends AppCompatActivity {
    ArrayList<Comment> mUserList = new ArrayList<>();
    CommentAdapter mThongBaoAdapter;
    GetListCommentRequest mGetListUserRequest;
    ListView mLvThongBao;
    IsLikeRequest mIsLikeRequest;
    ImageView mIvComment;
    String newsId, comment;
    Integer likes;
    EditText mEtComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getSupportActionBar().hide();

        mIvComment = (ImageView)findViewById(R.id.ivComment);
        mEtComment = (EditText)findViewById(R.id.etComment);

        newsId = getIntent().getExtras().getString("newsId");
        likes = getIntent().getExtras().getInt("likes");
        TextView actionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
        actionbar_tvTitile.setText(likes.toString() + " người thích điều này.");
        getListComment(newsId);

        mIvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment(newsId, mEtComment.getText().toString());
                mEtComment.setText("");
            }
        });
    }

    private void getListComment(String _id) {
        mLvThongBao = (ListView)findViewById(R.id.lvComment);

        mGetListUserRequest = new GetListCommentRequest(Method.GET, ApiLink.getListComment() + "/" + _id, null, null) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(ListCommentResponse entity, int statusCode, String message) {
                mUserList.clear();
                mUserList.addAll(entity.data);
                mThongBaoAdapter = new CommentAdapter(getApplicationContext(), mUserList);
                mThongBaoAdapter.notifyDataSetChanged();
                mLvThongBao.setAdapter(mThongBaoAdapter);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();


    }

    public void addComment(String _id, String content){
        HashMap<String, String> params = new HashMap<>();
        params.put("content", content);
        String url = ApiLink.addComment() + "/" + _id;
        mIsLikeRequest = new IsLikeRequest(Method.POST, url, null, params) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(LikesResponse entity, int statusCode, String message) {
                getListComment(newsId);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mIsLikeRequest.execute();
    }
}
