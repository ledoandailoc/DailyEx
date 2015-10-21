package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachMonAnAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.ArrayList;
import java.util.List;

public class TimMonAnActivity extends AppCompatActivity {
    SearchView mSvMonAn;
    ListView mLvMonAn;
    List<MonAn> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_mon_an);
        getSupportActionBar().hide();
        InitView();
        attachButton();

        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Tìm món ăn");

        list = new ArrayList<>();
        DatabaseHandle databaseHandle = new DatabaseHandle(this);
        list = databaseHandle.getMonAn();

        DanhSachMonAnAdapter anAdapter = new DanhSachMonAnAdapter(getBaseContext(), R.layout.item_monan, list);
        mLvMonAn.setAdapter(anAdapter);
        mLvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn monAn = list.get(position);
                Intent intent = new Intent(TimMonAnActivity.this, MonAnActivity.class);
                intent.putExtra("id", monAn.getId());
                startActivity(intent);
            }
        });

    }


    public void attachButton() {

    }

    public void InitView(){
        mLvMonAn = (ListView) findViewById(R.id.lv_monan);
    }
}
