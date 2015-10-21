package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachLanAdapter;

import java.util.ArrayList;

public class TinhTdeeActivity extends AppCompatActivity {
    CheckBox mCbNam, mCbNu;
    EditText mTxtChieuCao, mTxtCanNang, mTxtTuoi;
    Button mBtnTiepTuc;
    Spinner spinner;
    String argLoaiVanDong[];
    Double chieuCao, canNang, tdee, tuoi, heSo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_tdee);
        getSupportActionBar().hide();

        argLoaiVanDong = new String[]{"* Ít hoặc không vận động", "* Vận động nhẹ: 1-3 lần/tuần", "* Vận động vừa: 3-5 lần/tuần",
                "* Vận động nhiều: 6-7 lần/tuần", "* Vận động nặng: trên 7 lần/tuần"};

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Bước 1");

        InitView();
        attachButton();
        attachSpinner();
        attachCheckbox();
    }

    public void attachCheckbox(){
       mCbNam.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mCbNu.setChecked(false);
           }
       });
        mCbNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCbNam.setChecked(false);
            }
        });
    }

    public void attachSpinner(){
        ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, argLoaiVanDong);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayadapter);
        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterview, View view, int i, long l) {
                switch (i) {
                    case 0:
                        heSo = 1.2;
                        break;
                    case 1:
                        heSo = 1.375;
                        break;
                    case 2:
                        heSo = 1.55;
                        break;
                    case 3:
                        heSo = 1.725;
                        break;
                    case 4:
                        heSo = 1.9;
                        break;
                }
            }

            public void onNothingSelected(AdapterView adapterview) {
                heSo = 1.2;
            }
        });
    }

    public double TinhTdee(){
        return 0;
    }

    public void attachButton() {
        mBtnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double tdee;
                chieuCao = Double.parseDouble(String.valueOf(mTxtChieuCao.getText()));
                canNang = Double.parseDouble(String.valueOf(mTxtCanNang.getText()));
                tuoi = Double.parseDouble(String.valueOf(mTxtTuoi.getText()));
                if (mCbNam.isChecked() == true){
                    tdee = ((13.397 * canNang) + (4.799 * chieuCao) - (5.677 * tuoi) + 88.362) * heSo;
                }
                else {
                    tdee = ((9.247 * canNang) + (3.098 * chieuCao) - (4.330 * tuoi) + 447.593) * heSo;
                }
                Intent intent = new Intent(TinhTdeeActivity.this, TinhTdee2Activity.class);
                intent.putExtra("tdee", Math.round(tdee*100.0)/100.0);
                startActivity(intent);
            }
        });
    }
    public void InitView(){
        mCbNam = (CheckBox) findViewById(R.id.cb_nam);
        mCbNu = (CheckBox) findViewById(R.id.cb_nu);
        spinner = (Spinner) findViewById(R.id.spinner);
        mBtnTiepTuc = (Button) findViewById(R.id.btn_tieptuc);
        mTxtChieuCao = (EditText) findViewById(R.id.txt_chieucao);
        mTxtCanNang = (EditText) findViewById(R.id.txt_cannang);
        mTxtTuoi = (EditText) findViewById(R.id.txt_tuoi);
    }
}
