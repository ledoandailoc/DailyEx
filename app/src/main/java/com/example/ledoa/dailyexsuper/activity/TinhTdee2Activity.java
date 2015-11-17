package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TinhTdee2Activity extends AppCompatActivity {
    TextView mTvTdee, mTvCanNangPhuHop;
    TextView title;
    EditText mTxtCanNangMucTieu;
    Button mBtnHoanThanh;
    double tdee, chieuCao, canNangHienTai, canNangMucTieu, tongCaloCatGiam, soNgayTap, soCaloCatGiamMotNgay, canNangLiTuong1, canNangLiTuong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_tdee_2);

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            tdee = bundle.getDouble("tdee");
            chieuCao = bundle.getDouble("chieuCao");
            canNangHienTai = bundle.getDouble("canNang");
        }


        InitView();
        attachButton();
        InitData();
    }

    public void attachButton() {
        mBtnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canNangMucTieu = Double.parseDouble(String.valueOf(mTxtCanNangMucTieu.getText()));
                tongCaloCatGiam = (canNangHienTai - canNangMucTieu)*7700;
                soNgayTap = (canNangHienTai - canNangMucTieu) / 0.045;
                soCaloCatGiamMotNgay = (tongCaloCatGiam/soNgayTap);

/*
                tdee = tdee - soCaloCatGiamMotNgay;
*/

                Intent intent = new Intent(TinhTdee2Activity.this, TinhTdee3Activity.class);
                intent.putExtra("tdee", Math.round(tdee*100.0)/100.0);
                startActivity(intent);
                finish();
            }
        });
    }

    public void InitView(){
        title = (TextView) findViewById(R.id.actionbar_tvTitile);
        mTvCanNangPhuHop = (TextView) findViewById(R.id.tv_cannang_phuhop);
        mTxtCanNangMucTieu = (EditText) findViewById(R.id.txt_can_nang_muc_tieu);
        mBtnHoanThanh = (Button) findViewById(R.id.btn_tieptuc);
    }

    public void InitData(){
        title.setText("Bước 2");
        canNangLiTuong1 = (18.5 * chieuCao * chieuCao)/10000;
        canNangLiTuong2 = (23 * chieuCao * chieuCao)/10000;
        mTvCanNangPhuHop.setText(String.valueOf((int)canNangLiTuong1) + " kg - " + String.valueOf((int)canNangLiTuong2) + " kg");
    }

}
