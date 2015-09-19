package com.example.ledoa.dailyexsuper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class CaiDatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        getSupportActionBar().hide();

        TextView mActionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
        mActionbar_tvTitile.setText("Cài đặt riêng tư");
    }

}
