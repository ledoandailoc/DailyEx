package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ChuongTrinhGiamCan;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.ConvertDate;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay2;

import java.text.DateFormat;
import java.util.Date;

public class ThongTinChuongTrinhGiamCanActivity extends AppCompatActivity {

    Button mBtnHuyBo, mBtnTao;
    TextView mTvChuongTrinhTap, mTvCanNangCatGiam, mTvTongCaloCatGiam, mTvTongThoiGianDeXuat,mTvCalo1h;
    double canNangHienTai, canNangMucTieu, tongCaloCatGiam, soCaloCatGiamMotNgay, soNgayTap, soGioTapMotNgay,
            tongCaloDiBoTrenGio;
    String chuongTrinhTap = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinchuongtrinhgiamcan);
        getSupportActionBar().hide();

        final DatabaseHandle databaseHandle = new DatabaseHandle(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            canNangHienTai = bundle.getDouble("canNangHienTai");
            canNangMucTieu = bundle.getDouble("canNangMucTieu");
        }

        mTvCanNangCatGiam = (TextView) findViewById(R.id.tv_cannangcatgiam);
        mTvTongCaloCatGiam = (TextView) findViewById(R.id.tv_tongcalocatgiam);
        mTvTongThoiGianDeXuat = (TextView) findViewById(R.id.tv_tongthoigiandexuat);
        mTvCalo1h = (TextView) findViewById(R.id.tv_calo1h);
        mTvChuongTrinhTap = (TextView) findViewById(R.id.tv_chuongtrinhtap);
        TextView title = (TextView) findViewById(R.id.actionbar_tvTitile);
        title.setText("Bước 2");

        chuongTrinhTap = TinhChuongTrinhTap(canNangHienTai, canNangMucTieu);
        mTvCanNangCatGiam.setText("* Số cân nặng cần cắt giảm: " + String.valueOf((int)(canNangHienTai - canNangMucTieu)) + "kg");
        mTvTongCaloCatGiam.setText("* Tổng calo cần cắt giảm: " + String.valueOf((int)tongCaloCatGiam) + " calo");
        mTvTongThoiGianDeXuat.setText("* Tổng thời gian đề xuất: " + String.valueOf((int)soNgayTap) + " ngày");
        mTvCalo1h.setText("* Tổng calo tiêu hao trong 1h đi bộ: " + String.valueOf((int)tongCaloDiBoTrenGio) + " calo");
        mTvChuongTrinhTap.setText(chuongTrinhTap);

        mBtnHuyBo = (Button) findViewById(R.id.btn_huybo);
        mBtnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinChuongTrinhGiamCanActivity.this, ChuongTrinhGiamCan.class);
                startActivity(intent);
            }
        });

        mBtnTao = (Button) findViewById(R.id.btn_taochuongtrinh);
        mBtnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChuongTrinhGiamCan chuongTrinhGiamCan = new ChuongTrinhGiamCan();

                chuongTrinhGiamCan.setTenChuongTrinh("Đi bộ giảm cân");
                chuongTrinhGiamCan.setNoiDungChuongTrinh(chuongTrinhTap);
                chuongTrinhGiamCan.setSoNgayTap((int) soNgayTap);
                chuongTrinhGiamCan.setMaMonTap("DB");
                chuongTrinhGiamCan.setCanNangHienTai((int) canNangHienTai);
                chuongTrinhGiamCan.setCanNangMucTieu((int) canNangMucTieu);
                chuongTrinhGiamCan.setSoCanNangCatGiam((int) (canNangHienTai - canNangMucTieu));
                chuongTrinhGiamCan.setSoGioMoiNgay(soGioTapMotNgay);
                chuongTrinhGiamCan.setNgay(ConvertDate.ConvertDateToString(new Date()));
                chuongTrinhGiamCan.setTienDo(0);

                databaseHandle.addChuongTrinhGiamCan(chuongTrinhGiamCan);
                Intent intent = new Intent(ThongTinChuongTrinhGiamCanActivity.this, BaiTapGiamCanActivity.class);
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

    public String TinhChuongTrinhTap(double canNangHienTai, double canNangMucTieu){
        tongCaloCatGiam = (canNangHienTai - canNangMucTieu)*7700;
        soNgayTap = (canNangHienTai - canNangMucTieu) / 0.045;
        soCaloCatGiamMotNgay = (tongCaloCatGiam/soNgayTap);

        if (canNangHienTai <= 60){
            tongCaloDiBoTrenGio = 4.7 * 60;
        }
        else if (60 < canNangHienTai && canNangHienTai >= 70){
            tongCaloDiBoTrenGio = 5.5 * 60;
        }
        else if (70 < canNangHienTai && canNangHienTai >= 80){
            tongCaloDiBoTrenGio = 6.3 * 60;
        }
        else if (80 < canNangHienTai && canNangHienTai >= 90){
            tongCaloDiBoTrenGio = 7.1 * 60;
        }
        else if (90 < canNangHienTai && canNangHienTai >= 100){
            tongCaloDiBoTrenGio = 7.8 * 60;
        }
        else {
            tongCaloDiBoTrenGio = 8.6 * 60;
        }

        soGioTapMotNgay= soCaloCatGiamMotNgay/tongCaloDiBoTrenGio;
        chuongTrinhTap = "Đi bộ mỗi ngày " + DoiGioPhutGiay2.GiaySangGio((int) (soGioTapMotNgay * 60 * 60)) + " trong vòng " + (int)soNgayTap
                          + " ngày"  ;
        return chuongTrinhTap;
    }
}
