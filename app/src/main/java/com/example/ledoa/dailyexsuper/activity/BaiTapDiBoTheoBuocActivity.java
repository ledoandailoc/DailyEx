package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachBaiTapAdapter;
import com.example.ledoa.dailyexsuper.adapter.DanhSachLanAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.ArrayList;
import java.util.List;

public class BaiTapDiBoTheoBuocActivity extends AppCompatActivity {

    DatabaseHandle databaseHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitapdibo_buoc);

        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Mục tiêu bước đi");

        databaseHandle = new DatabaseHandle(this);

        final List list = databaseHandle.getBaiTapTheoLoai("DB","B");

        ListView listView = (ListView) findViewById(R.id.lv_baitap);

        DanhSachBaiTapAdapter adapter = new DanhSachBaiTapAdapter(getBaseContext(), R.layout.item_baitap, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiTap baiTap = new BaiTap();
                baiTap = (BaiTap) list.get(position);

                Intent intent = new Intent(BaiTapDiBoTheoBuocActivity.this, DiBoActivity.class);
                intent.putExtra("soBuoc", Integer.parseInt(String.valueOf(baiTap.getMucTieu())));
                intent.putExtra("IdBaiTap", String.valueOf(baiTap.getTenBaiTap()));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bai_tap_di_bo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
