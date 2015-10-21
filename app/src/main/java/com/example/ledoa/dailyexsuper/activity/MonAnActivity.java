package com.example.ledoa.dailyexsuper.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.DinhDuong;
import com.example.ledoa.dailyexsuper.sqlite.DTO.DonVi;
import com.example.ledoa.dailyexsuper.sqlite.DTO.MonAn;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;

import java.util.ArrayList;

public class MonAnActivity extends AppCompatActivity {
    TextView mTvCalo, mTvTenMonAn, mTvSoLuong, mTvDonvi, mTvChatDam, mTvTinhBot, mTvChatBeo, mTvChatXo, mTvDuong, mTvCholesterol;
    ImageView mBtnEditSoLuong, mBtnEditDonVi;
    EditText mTxtSoLuong;
    ListView mLvDonVi;

    MonAn monAn;
    DinhDuong dinhDuong;

    int monAnId;
    int soLuong = 1;
    int donVi;
    ArrayList<DonVi> listDonVi;
    ArrayList<String> listTenDonVi;
    DatabaseHandle databaseHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        getSupportActionBar().hide();
        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Tìm món ăn");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            monAnId = bundle.getInt("id");
        }

        databaseHandle = new DatabaseHandle(this);
        // Mon an
        monAn = databaseHandle.getMonAnTheoId(monAnId);

        // Don vi
        listDonVi = new ArrayList<DonVi>();
        listDonVi = databaseHandle.getDonVi(monAnId);
        listTenDonVi = new ArrayList<String>();
        for (DonVi donvi : listDonVi) {
            listTenDonVi.add(donvi.getName().toString());
        }

        //Dinh Duong
        dinhDuong = databaseHandle.getDinhDuong(monAnId,listDonVi.get(0).getId());

        InitView();
        attachButton();
        InitData();

        mTvDonvi.setText(listTenDonVi.get(0));
    }


    public void attachButton() {
        mBtnEditSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog= new Dialog(MonAnActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert_monan_soluong);
                dialog.show();
                Button btn_ok = (Button) dialog.findViewById(R.id.btn_alert_ok);
                mTxtSoLuong = (EditText) dialog.findViewById(R.id.txt_soluong);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soLuong = Integer.parseInt(mTxtSoLuong.getText().toString());
                        mTvSoLuong.setText(String.valueOf(soLuong));
                        InitData();
                        dialog.dismiss();
                    }
                });

            }
        });

        mBtnEditDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MonAnActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert_monan_donvi);
                dialog.show();

                mLvDonVi = (ListView) dialog.findViewById(R.id.lv_donvi);
                ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, listTenDonVi);
                mLvDonVi.setAdapter(adapter);
                mLvDonVi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTvDonvi.setText(listTenDonVi.get(position));
                        dinhDuong = databaseHandle.getDinhDuong(monAnId,listDonVi.get(position).getId());
                        mTvDonvi.setText(listTenDonVi.get(position));
                        InitData();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void InitView(){
        mTvCalo = (TextView) findViewById(R.id.tv_calo);
        mTvTenMonAn = (TextView) findViewById(R.id.tv_tenmonan);
        mBtnEditSoLuong = (ImageView) findViewById(R.id.btn_edit_soluong);
        mBtnEditDonVi = (ImageView) findViewById(R.id.btn_edit_donvi);
        mTvSoLuong = (TextView) findViewById(R.id.tv_soluong);
        mTvDonvi = (TextView) findViewById(R.id.tv_donvi);
        mTvChatBeo = (TextView) findViewById(R.id.tv_chatbeo);
        mTvChatDam = (TextView) findViewById(R.id.tv_dam);
        mTvTinhBot = (TextView) findViewById(R.id.tv_tinhbot);
        mTvChatXo = (TextView) findViewById(R.id.tv_chatxo);
        mTvDuong = (TextView) findViewById(R.id.tv_duong);
        mTvCholesterol = (TextView) findViewById(R.id.tv_cholesterol);


    }

    public void InitData(){
        mTvCalo.setText(String.valueOf(dinhDuong.getCalories()*soLuong));
        mTvTenMonAn.setText(String.valueOf(monAn.getName()));

        mTvChatBeo.setText(String.valueOf(dinhDuong.getFat()*soLuong));
        mTvChatDam.setText(String.valueOf(dinhDuong.getProtein()*soLuong));
        mTvTinhBot.setText(String.valueOf(dinhDuong.getCarbohydrates()*soLuong));
        mTvChatXo.setText(String.valueOf(dinhDuong.getFiber()*soLuong));
        mTvDuong.setText(String.valueOf(dinhDuong.getSugars()*soLuong));
        mTvCholesterol.setText(String.valueOf(dinhDuong.getCholesterol()*soLuong));

    }
}
