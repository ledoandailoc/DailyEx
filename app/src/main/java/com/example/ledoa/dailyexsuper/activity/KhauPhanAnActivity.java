package com.example.ledoa.dailyexsuper.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.NonScrollListView;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachKhauPhanAnAdapter;
import com.example.ledoa.dailyexsuper.adapter.DanhSachMonAnAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAnPref;
import com.example.ledoa.dailyexsuper.util.KhauPhanAnPref;

import java.util.ArrayList;
import java.util.List;

public class KhauPhanAnActivity extends AppCompatActivity {
    Button mBtnTinhTdee, mBtnTinhBmi, mBtnBuaSang,mBtnBuaTrua,mBtnBuaToi,mBtnCacMonAnThem;
    TextView mTvTongCalo, mTvBmi, mTvTdee;
    ImageView mBtnTdee, mBtnBmi;

    NonScrollListView mLvBuoiSang, mLvBuoiTrua, mLvBuoiToi, mLvCacMonAnThem;

    List<MonAnPref> listBuoiSang = new ArrayList<>();
    List<MonAnPref> listBuoiTrua = new ArrayList<>();
    List<MonAnPref> listBuoiToi = new ArrayList<>();
    List<MonAnPref> listCacMonAnThem = new ArrayList<>();

    int tongCalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khauphanan);
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Chỉ số cơ thể và khẩu phần ăn");

        KhauPhanAnPref phanAnPref = new KhauPhanAnPref();
        tongCalo = phanAnPref.getTongCalo();
        listBuoiSang = phanAnPref.getListMonAnPrefTheoBuoi("sang");
        listBuoiTrua = phanAnPref.getListMonAnPrefTheoBuoi("trua");
        listBuoiToi = phanAnPref.getListMonAnPrefTheoBuoi("toi");
        listCacMonAnThem = phanAnPref.getListMonAnPrefTheoBuoi("anthem");

        InitView();
        attachButton();
        InitData();
    }

    public void attachButton() {
        mBtnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(KhauPhanAnActivity.this);
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
                final Dialog dialog = new Dialog(KhauPhanAnActivity.this);
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
                intent.putExtra("buoi","sang");
                startActivity(intent);
            }
        });
        mBtnBuaTrua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TimMonAnActivity.class);
                intent.putExtra("buoi", "trua");
                startActivity(intent);
            }
        });
        mBtnBuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TimMonAnActivity.class);
                intent.putExtra("buoi", "toi");
                startActivity(intent);
            }
        });
        mBtnCacMonAnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhauPhanAnActivity.this, TimMonAnActivity.class);
                intent.putExtra("buoi", "anthem");
                startActivity(intent);
            }
        });
    }

    public void InitView(){
        mTvTongCalo = (TextView) findViewById(R.id.tv_tong_calo);
        mTvBmi = (TextView) findViewById(R.id.tv_bmi);
        mTvTdee = (TextView) findViewById(R.id.tv_tdee);

        mBtnTdee = (ImageView) findViewById(R.id.btn_tdee);
        mBtnBmi = (ImageView) findViewById(R.id.btn_bmi);
        mBtnTinhBmi = (Button) findViewById(R.id.btn_tinh_bmi);
        mBtnTinhTdee = (Button) findViewById(R.id.btn_tinh_tdee);
        mBtnBuaSang = (Button) findViewById(R.id.btn_buasang);
        mBtnBuaTrua = (Button) findViewById(R.id.btn_buatrua);
        mBtnBuaToi = (Button) findViewById(R.id.btn_buatoi);
        mBtnCacMonAnThem = (Button) findViewById(R.id.btn_cacmonthem);

        mLvBuoiSang = (NonScrollListView) findViewById(R.id.lv_buoisang);
        mLvBuoiTrua = (NonScrollListView) findViewById(R.id.lv_buoitrua);
        mLvBuoiToi = (NonScrollListView) findViewById(R.id.lv_buoitoi);
        mLvCacMonAnThem = (NonScrollListView) findViewById(R.id.lv_cacmonanthem);

    }

    public void InitData(){
        mTvTongCalo.setText(String.valueOf(tongCalo) + " calo");

        DanhSachKhauPhanAnAdapter adapter = new DanhSachKhauPhanAnAdapter(getBaseContext(), R.layout.item_khauphanan, listBuoiSang);
        mLvBuoiSang.setAdapter(adapter);

        DanhSachKhauPhanAnAdapter adapter1 = new DanhSachKhauPhanAnAdapter(getBaseContext(), R.layout.item_khauphanan, listBuoiTrua);
        mLvBuoiTrua.setAdapter(adapter1);

        DanhSachKhauPhanAnAdapter adapter2 = new DanhSachKhauPhanAnAdapter(getBaseContext(), R.layout.item_khauphanan, listBuoiToi);
        mLvBuoiToi.setAdapter(adapter2);

        DanhSachKhauPhanAnAdapter adapter3 = new DanhSachKhauPhanAnAdapter(getBaseContext(), R.layout.item_khauphanan, listCacMonAnThem);
        mLvCacMonAnThem.setAdapter(adapter3);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Intent i = getIntent();
        startActivity(i);
        finish();

    }

}
