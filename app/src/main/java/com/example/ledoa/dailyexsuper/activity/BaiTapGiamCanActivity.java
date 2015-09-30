package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachChuongTrinhGiamCanAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.ArrayList;
import java.util.List;

public class BaiTapGiamCanActivity extends AppCompatActivity {
    Button mBtnTaoChuongTrinh;
    ListView mLvChuongTrinhGiamCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitapgiamcan);
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Chương trình tập giảm cân");

        DatabaseHandle databaseHandle = new DatabaseHandle(this);

        final List list =  databaseHandle.getChuongTrinhGiamCan();

        mLvChuongTrinhGiamCan = (ListView) findViewById(R.id.lv_chuongtrinhgiamcan);
        DanhSachChuongTrinhGiamCanAdapter adapter = new DanhSachChuongTrinhGiamCanAdapter(getBaseContext(), R.layout.item_chuongtrinhtap, list);
        mLvChuongTrinhGiamCan.setAdapter(adapter);
        mLvChuongTrinhGiamCan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();
                chuongTrinhGiamCan = (ChuongTrinhGiamCan) list.get(position);

                if (chuongTrinhGiamCan.getTienDo() != chuongTrinhGiamCan.getSoNgayTap()) {
                    Intent intent = new Intent(BaiTapGiamCanActivity.this, DiBoActivity.class);
                    intent.putExtra("IdChuongTrinhGiamCan", chuongTrinhGiamCan.getId());
                    intent.putExtra("soThoiGian", (int) (chuongTrinhGiamCan.getSoGioMoiNgay() * 60 * 60));
                    startActivity(intent);
                } else return;
            }
        });

        mBtnTaoChuongTrinh = (Button) findViewById(R.id.btn_taochuongtrinhgiamcan);
        mBtnTaoChuongTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiTapGiamCanActivity.this, TaoChuongTrinhGiamCanActivity.class);
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
