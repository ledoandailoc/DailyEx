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
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.DanhSachMonAnAdapter;
import com.example.ledoa.dailyexsuper.adapter.UserAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimMonAnActivity extends AppCompatActivity {
    SearchView mSvMonAn;
    ArrayList<MonAn> mStudentListSearching, mStudentList;
    SearchView searchView;
    ListView mLvMonAn;
    List<MonAn> list;
    String buoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_mon_an);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        buoi = bundle.getString("buoi");


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
                intent.putExtra("buoi", buoi);
                startActivity(intent);
                finish();
            }
        });

        attackSearchView();
    }


    public void attachButton() {
    }

    public void attackSearchView(){
        searchView = (SearchView) findViewById(R.id.sv_monan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText.toString().toLowerCase(Locale.getDefault()));
                Toast.makeText(TimMonAnActivity.this, newText.toString().toLowerCase(Locale.getDefault()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void InitView(){
        mLvMonAn = (ListView) findViewById(R.id.lv_monan);
    }

    public void filter(String charSearching) {
        mStudentListSearching = new ArrayList<>();
        charSearching = charSearching.toLowerCase(Locale.getDefault());
        mStudentListSearching.clear();
        if (charSearching.length() == 0) {
            this.mStudentListSearching.addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i)).getName().toLowerCase(Locale.getDefault()).contains(charSearching)) {
                    mStudentListSearching.add(list.get(i));
                }
            }
        }
        DanhSachMonAnAdapter anAdapter = new DanhSachMonAnAdapter(getBaseContext(), R.layout.item_monan, mStudentListSearching);
        mLvMonAn.setAdapter(anAdapter);
    }
}
