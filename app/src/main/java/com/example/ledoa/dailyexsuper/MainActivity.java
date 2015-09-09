package com.example.ledoa.dailyexsuper;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.activity.DanhBaActivity;
import com.example.ledoa.dailyexsuper.activity.ThongBaoActivity;
import com.example.ledoa.dailyexsuper.adapter.LeftAdapter;
import com.example.ledoa.dailyexsuper.adapter.MainFragmentAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemMenuLeft;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    RelativeLayout mRlTabLuyenTap, mRlTabLichTap, mRlTabBaiTap, mRlTabMangXaHoi;
    ImageView mIvTabLuyenTap, mIvTabLichTap, mIvTabBaiTap, mIvTabMangXaHoi;
    TextView mTvTabLuyenTap, mTvTabLichTap, mTvTabBaiTap, mTvTabMangXaHoi, mTvActionBarTitle;
    DrawerLayout drawerLayout;
    LeftAdapter adapterLeft;
    List<ItemMenuLeft> listDrawer;
    ListView ListViewLeft;

    int itemAvatar[] = {R.drawable.icon_timban, R.drawable.icon_thongbao, R.drawable.icon_tab,R.drawable.icon_thongbao,
            R.drawable.icon_logout};
    String itemText[] = {"Thông báo mới","Thêm bạn",
            "Danh bạ","Cài đặt riêng tư","Thoát"};
    AppContants.TAB_TYPE mCurentTab = AppContants.TAB_TYPE.TAB_NONE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mTvActionBarTitle = (TextView) findViewById(R.id.actionbar_tvTitile);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ListViewLeft = (ListView)findViewById(R.id.left_drawer);


        listDrawer = new ArrayList<ItemMenuLeft>();
        for(int i=0; i< itemText.length;i++){
            ItemMenuLeft item = new ItemMenuLeft();
            item.setHinhAnh(itemAvatar[i]);
            item.setTenMenu(itemText[i]);
            listDrawer.add(item);
        }
        adapterLeft = new LeftAdapter(this, R.layout.custom_layout, listDrawer);

        ListViewLeft.setAdapter(adapterLeft);

        ListViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent iThongBao = new Intent(MainActivity.this, ThongBaoActivity.class);
                Intent iThemBan = new Intent(MainActivity.this, ThongBaoActivity.class);
                Intent iDanhBa = new Intent(MainActivity.this, DanhBaActivity.class);
                /*Intent iLogin = new Intent(MainActivity.this, Login.class);*/

                switch (position) {
                    case 0:
                        startActivity(iThongBao);
                        break;
                    case 1:
                        startActivity(iThemBan);
                        break;
                    case 2:
                        startActivity(iDanhBa);
                        break;
                   /* case 5:
                        startActivity(iLogin);
                        break;*/

                    default:
                        break;
                }

            }
        });


        attachFragment();
        attachTab();
    }

    public void attachFragment(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mTvActionBarTitle.setText("Bài Tập");
                        unSelectAllTab();
                        mIvTabBaiTap.setImageResource(R.drawable.icon_tab_baitap_vang);
                        mTvTabBaiTap.setTextColor(getResources().getColor(R.color.tab_text_active));
                        break;
                      
                    case 1:
                        mTvActionBarTitle.setText("Lịch Tập");
                        unSelectAllTab();
                        mIvTabLichTap.setImageResource(R.drawable.icon_tab_lichtap_vang);
                        mTvTabLichTap.setTextColor(getResources().getColor(R.color.tab_text_active));
                        break;
                    case 2:
                        mTvActionBarTitle.setText("Luyện Tập Tự Do");
                        unSelectAllTab();
                        mIvTabLuyenTap.setImageResource(R.drawable.icon_tab_tapluyen_vang);
                        mTvTabLuyenTap.setTextColor(getResources().getColor(R.color.tab_text_active));
                        break;
                    case 3:
                        mTvActionBarTitle.setText("Mạng Xã Hội");
                        unSelectAllTab();
                        mIvTabMangXaHoi.setImageResource(R.drawable.icon_mangxahoi_vang);
                        mTvTabMangXaHoi.setTextColor(getResources().getColor(R.color.tab_text_active));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void attachTab(){
        mRlTabLuyenTap = (RelativeLayout) findViewById(R.id.tab_rl_luyentap);
        mIvTabLuyenTap = (ImageView) findViewById(R.id.tab_iv_luyentap);
        mTvTabLuyenTap = (TextView) findViewById(R.id.tab_tv_luyentap);

        mRlTabLichTap = (RelativeLayout) findViewById(R.id.tab_rl_lichtap);
        mIvTabLichTap = (ImageView) findViewById(R.id.tab_iv_lichtap);
        mTvTabLichTap = (TextView) findViewById(R.id.tab_tv_lichtap);

        mRlTabBaiTap = (RelativeLayout) findViewById(R.id.tab_rl_baitap);
        mIvTabBaiTap = (ImageView) findViewById(R.id.tab_iv_baitap);
        mTvTabBaiTap = (TextView) findViewById(R.id.tab_tv_baitap);

        mRlTabMangXaHoi = (RelativeLayout) findViewById(R.id.tab_rl_mangxahoi);
        mIvTabMangXaHoi = (ImageView) findViewById(R.id.tab_iv_mangxahoi);
        mTvTabMangXaHoi = (TextView) findViewById(R.id.tab_tv_mangxahoi);

        mIvTabBaiTap.setImageResource(R.drawable.icon_tab_baitap_vang);
        mTvTabBaiTap.setTextColor(getResources().getColor(R.color.tab_text_active));

        mRlTabLuyenTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvActionBarTitle.setText("Luyện Tập");
                viewPager.setCurrentItem(2);
                unSelectAllTab();
                mIvTabLuyenTap.setImageResource(R.drawable.icon_tab_tapluyen_vang);
                mTvTabLuyenTap.setTextColor(getResources().getColor(R.color.tab_text_active));
            }
        });

        mRlTabLichTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvActionBarTitle.setText("Lịch Tập");
                viewPager.setCurrentItem(1);
                unSelectAllTab();
                mIvTabLichTap.setImageResource(R.drawable.icon_tab_lichtap_vang);
                mTvTabLichTap.setTextColor(getResources().getColor(R.color.tab_text_active));
            }
        });

        mRlTabBaiTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvActionBarTitle.setText("Bài Tập");
                viewPager.setCurrentItem(0);
                unSelectAllTab();
                mIvTabBaiTap.setImageResource(R.drawable.icon_tab_baitap_vang);
                mTvTabBaiTap.setTextColor(getResources().getColor(R.color.tab_text_active));
            }
        });

        mRlTabMangXaHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvActionBarTitle.setText("Mạng Xã Hội");
                viewPager.setCurrentItem(3);
                unSelectAllTab();
                mIvTabMangXaHoi.setImageResource(R.drawable.icon_mangxahoi_vang);
                mTvTabMangXaHoi.setTextColor(getResources().getColor(R.color.tab_text_active));
            }
        });
    }

    public void unSelectAllTab(){
        mIvTabLuyenTap.setImageResource(R.drawable.icon_tab_tapluyen);
        mTvTabLuyenTap.setTextColor(getResources().getColor(R.color.tab_text));
        mIvTabLichTap.setImageResource(R.drawable.icon_tab_lichtap);
        mTvTabLichTap.setTextColor(getResources().getColor(R.color.tab_text));
        mIvTabBaiTap.setImageResource(R.drawable.icon__tab_baitap);
        mTvTabBaiTap.setTextColor(getResources().getColor(R.color.tab_text));
        mIvTabMangXaHoi.setImageResource(R.drawable.icon_mangxahoi);
        mTvTabMangXaHoi.setTextColor(getResources().getColor(R.color.tab_text));
    }

}
