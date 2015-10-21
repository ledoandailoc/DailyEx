package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TinhTdee2Activity extends AppCompatActivity {
    TextView mTvTdee;
    Button mBtnHoanThanh;
    double tdee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_tdee_2);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            tdee = bundle.getDouble("tdee");
        }

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Kết quả");

        InitView();
        attachButton();
    }

    public void attachButton() {
        mBtnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TinhTdee2Activity.this, KhauPhanAnActivity.class);
                startActivity(intent);
            }
        });
    }
    public void InitView(){
        mBtnHoanThanh = (Button) findViewById(R.id.btn_hoanthanh);
    }
}
