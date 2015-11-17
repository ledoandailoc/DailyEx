package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TinhTdee3Activity extends AppCompatActivity {
    TextView mTvTdee, mTvKetQua;
    TextView title;
    Button mBtnHoanThanh;
    double tdee, canNang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_tdee_3);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            tdee = bundle.getDouble("tdee");
            canNang = bundle.getDouble("canNang");
        }



        InitView();
        attachButton();
        InitData();
    }

    public void attachButton() {
        mBtnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TinhTdee3Activity.this, KhauPhanAnActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void InitView(){
        title = (TextView) findViewById(R.id.actionbar_tvTitile);
        mTvKetQua = (TextView) findViewById(R.id.tv_ket_qua);
        mTvTdee = (TextView) findViewById(R.id.tv_tdee);
        mBtnHoanThanh = (Button) findViewById(R.id.btn_hoanthanh);
    }

    public void InitData(){
        title.setText("Kết quả");
        mTvKetQua.setText(String.valueOf("Bạn cần nạp vào cơ thể " + (int)tdee + " calo 1 ngày từ thức ăn, nước uống để có thể duy trì cân nặng hiện tại là "+ (int)canNang +" với cường độ luyện tập và lối sống không đổi"));
        mTvTdee.setText(String.valueOf("TDEE: " + tdee));
    }

}
