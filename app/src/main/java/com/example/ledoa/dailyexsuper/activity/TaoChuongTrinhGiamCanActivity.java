package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TaoChuongTrinhGiamCanActivity extends AppCompatActivity {
    Button mBtnTiepTuc;
    EditText mTxtCanNangHienTai, mTxtCanNangMucTieu;

    double canNangHienTai, canNangMucTieu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taochuongtrinhgiamcan);
        getSupportActionBar().hide();

        mTxtCanNangHienTai = (EditText) findViewById(R.id.txt_cannanghientai);
        mTxtCanNangMucTieu = (EditText) findViewById(R.id.txt_cannangmuctieu);

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Bước 1");

        mBtnTiepTuc = (Button) findViewById(R.id.btn_tieptuc);
        mBtnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canNangHienTai = Integer.parseInt(String.valueOf(mTxtCanNangHienTai.getText()));
                canNangMucTieu = Integer.parseInt(String.valueOf(mTxtCanNangMucTieu.getText()));
                Intent intent = new Intent(TaoChuongTrinhGiamCanActivity.this, ThongTinChuongTrinhGiamCanActivity.class);
                intent.putExtra("canNangHienTai", canNangHienTai);
                intent.putExtra("canNangMucTieu", canNangMucTieu);
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
