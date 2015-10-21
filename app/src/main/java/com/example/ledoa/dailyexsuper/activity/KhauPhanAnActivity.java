package com.example.ledoa.dailyexsuper.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;

import java.util.ArrayList;

public class KhauPhanAnActivity extends AppCompatActivity {
    Button mBtnTinhTdee, mBtnTinhBmi, mBtnBuaSang,mBtnBuaTrua,mBtnBuaToi,mBtnCacMonAnThem;
    ImageView mBtnTdee, mBtnBmi;
    ListView mLvBuoiSang, mLvBuoiTrua, mLvBuoiToi, mLvCacMonAnThem;

    ArrayList<String> listBuoiSang = new ArrayList<>();
    ArrayList<String> listBuoiTrua = new ArrayList<>();
    ArrayList<String> listBuoiToi = new ArrayList<>();
    ArrayList<String> listCacMonAnThem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khauphanan);
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Chỉ số cơ thể và khẩu phần ăn");

        InitView();
        attachButton();
    }

    public void attachButton() {
        mBtnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(KhauPhanAnActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert_bmi);
                dialog.show();
                Button btn_ok = (Button) dialog.findViewById(R.id.btn_alert_ok);
                int soBuoc;
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        mBtnTdee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(KhauPhanAnActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert_tdee);
                dialog.show();
                Button btn_ok = (Button) dialog.findViewById(R.id.btn_alert_ok);
                int soBuoc;
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        mBtnTinhBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TinhBmiActivity.class);
                startActivity(intent);
            }
        });
        mBtnTinhTdee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TinhTdeeActivity.class);
                startActivity(intent);
            }
        });
        mBtnBuaSang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TimMonAnActivity.class);
                startActivity(intent);
            }
        });
        mBtnBuaTrua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnBuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnCacMonAnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void InitView(){
        mBtnTdee = (ImageView) findViewById(R.id.btn_tdee);
        mBtnBmi = (ImageView) findViewById(R.id.btn_bmi);
        mBtnTinhBmi = (Button) findViewById(R.id.btn_tinh_bmi);
        mBtnTinhTdee = (Button) findViewById(R.id.btn_tinh_tdee);
        mBtnBuaSang = (Button) findViewById(R.id.btn_buasang);
        mBtnBuaTrua = (Button) findViewById(R.id.btn_buatrua);
        mBtnBuaToi = (Button) findViewById(R.id.btn_buatoi);
        mBtnCacMonAnThem = (Button) findViewById(R.id.btn_cacmonthem);

        mLvBuoiSang = (ListView) findViewById(R.id.lv_buoisang);
        mLvBuoiTrua = (ListView) findViewById(R.id.lv_buoitrua);
        mLvBuoiToi = (ListView) findViewById(R.id.lv_buoitoi);
        mLvCacMonAnThem = (ListView) findViewById(R.id.lv_cacmonanthem);

    }

    public void InitData(){

    }
}
