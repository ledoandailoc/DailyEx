package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TinhBmi2Activity extends AppCompatActivity {
    Button mBtnHoanThanh;
    TextView mTvBmi, mTvTrangThaiCoThe;

    double bmi;
    String trangThaiCoThe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_bmi_2);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            bmi = bundle.getDouble("bmi");
        }

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Kết quả");

        InitView();
        attachButton();
        InitData();
    }

    public void attachButton() {
        mBtnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TinhBmi2Activity.this, KhauPhanAnActivity.class);
                startActivity(intent);
            }
        });
    }
    public void InitView(){
        mTvBmi = (TextView) findViewById(R.id.tv_tdee);
        mTvTrangThaiCoThe = (TextView) findViewById(R.id.tv_trangthaicothe);
        mBtnHoanThanh = (Button) findViewById(R.id.btn_hoanthanh);
    }

    public void InitData(){
        if (bmi < 18.5){
            trangThaiCoThe = "Trạng thái cơ thể: Gầy";
        }
        else if (18.5 <= bmi && bmi < 23 ){
            trangThaiCoThe = "Trạng thái cơ thể: Bình thuòng";
        }
        else if (bmi == 23 ){
            trangThaiCoThe = "Trạng thái cơ thể: Thừa cân";
        }
        else if (23 < bmi && bmi < 25  ){
            trangThaiCoThe = "Trạng thái cơ thể: Tiền béo phì";
        }
        else if (25 <= bmi && bmi < 30  ){
            trangThaiCoThe = "Trạng thái cơ thể: Béo Phì loại I";
        }
        else if (30 <= bmi && bmi < 40  ){
            trangThaiCoThe = "Trạng thái cơ thể: Béo Phì loại II";
        }
        else if (bmi >= 40 ){
            trangThaiCoThe = "Trạng thái cơ thể: Béo Phì loại III";
        }

        mTvBmi.setText(String.valueOf("BMI: " + bmi));
        mTvTrangThaiCoThe.setText(String.valueOf(trangThaiCoThe));
        mBtnHoanThanh = (Button) findViewById(R.id.btn_hoanthanh);
    }
}
