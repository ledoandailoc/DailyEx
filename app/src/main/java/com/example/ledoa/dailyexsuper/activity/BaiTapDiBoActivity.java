package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class BaiTapDiBoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitapdibo);

        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Loại Bài Tập");

        RelativeLayout relativeLayout_buoc = (RelativeLayout) findViewById(R.id.rl_baitap_buoc);
        relativeLayout_buoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiTapDiBoActivity.this, BaiTapDiBoTheoBuocActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout relativeLayout_thoigian = (RelativeLayout) findViewById(R.id.rl_baitap_thoigian);
        relativeLayout_thoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiTapDiBoActivity.this, BaiTapDiBoTheoThoiGianActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout ivBack = (RelativeLayout) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
