package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;

public class TinhBmiActivity extends AppCompatActivity {
    Button mBtnTiepTuc;
    EditText mTxtChieuCao, mTxtCanNang;
    Double chieuCao, canNang, bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_bmi);
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Tính Bmi");

        InitView();
        attachButton();

    }

    public double TinhBmi(double canNang, double chieuCao){
        return  (canNang*10000)/(chieuCao*chieuCao);
    }

    public void attachButton() {
        mBtnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chieuCao = Double.parseDouble(String.valueOf(mTxtChieuCao.getText()));
                canNang = Double.parseDouble(String.valueOf(mTxtCanNang.getText()));
                bmi = Math.round(TinhBmi(canNang,chieuCao)*100.0)/100.0;

                Intent intent = new Intent(TinhBmiActivity.this, TinhBmi2Activity.class);
                intent.putExtra("bmi",bmi);
                startActivity(intent);
            }
        });
    }

    public void InitView(){
        mBtnTiepTuc = (Button) findViewById(R.id.btn_tieptuc);
        mTxtChieuCao = (EditText) findViewById(R.id.txt_chieucao);
        mTxtCanNang = (EditText) findViewById(R.id.txt_cannang);
    }
}
