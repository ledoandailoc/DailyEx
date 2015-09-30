package com.example.ledoa.dailyexsuper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachBaiTapTangSucBenAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhTangSucBen;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.List;

public class ChuongTrinhTangSucBenActivity extends AppCompatActivity {

    DatabaseHandle databaseHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuongtrinhtangsucben);
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Bài tập tăng sức bền");

        databaseHandle = new DatabaseHandle(this);

        final List list = databaseHandle.getChuongTrinhTangSucBen();

        ListView listView = (ListView) findViewById(R.id.lv_baitap);

        DanhSachBaiTapTangSucBenAdapter adapter = new DanhSachBaiTapTangSucBenAdapter(getBaseContext(), R.layout.item_chuongtrinhtangsucben, list);
        listView.setAdapter(adapter);

        RelativeLayout ivBack = (RelativeLayout) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
