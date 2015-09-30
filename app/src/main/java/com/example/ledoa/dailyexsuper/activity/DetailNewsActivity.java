package com.example.ledoa.dailyexsuper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.MangXaHoiAdapter;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListNewsRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListNewsResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.News;

import java.util.ArrayList;

public class DetailNewsActivity extends AppCompatActivity {


    public TextView tv_title, tv_thoigian, tv_like_comment, tv_description, tv_content;
    public ImageView iv_avatar, iv_thumbnail, iv_like, iv_comment;


    String  title, content, thumbnail, description, likes;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        getSupportActionBar().hide();

        title = getIntent().getExtras().getString("title");
        content = getIntent().getExtras().getString("content");
        thumbnail = getIntent().getExtras().getString("thumbnail");
        description = getIntent().getExtras().getString("description");

        ViewHolder();
    }

        public void ViewHolder () {
            tv_title = (TextView)findViewById(R.id.tv_title);
            tv_description = (TextView)findViewById(R.id.tv_description);
            tv_content = (TextView) findViewById(R.id.tv_content);
            tv_thoigian = (TextView)findViewById(R.id.tv_thoigian);
            tv_like_comment = (TextView)findViewById(R.id.tv_like_comment);
            iv_avatar = (ImageView)findViewById(R.id.iv_avatar_user);
            iv_thumbnail = (ImageView)findViewById(R.id.iv_thumbnail);
            iv_like = (ImageView)findViewById(R.id.iv_like);
            iv_comment = (ImageView)findViewById(R.id.iv_comment);

            tv_title.setText(title);


            tv_description.setText(description.replace("\\n","\n"));
            tv_content.setText(content);
            if (thumbnail != null) {
                ImageLoaderUtil.display(thumbnail, iv_thumbnail);
            } else {
                iv_thumbnail.setImageResource(R.drawable.avt);
            }
        }

}
