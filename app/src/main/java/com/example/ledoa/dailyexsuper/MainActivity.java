package com.example.ledoa.dailyexsuper;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ledoa.dailyexsuper.activity.CaiDatActivity;
import com.example.ledoa.dailyexsuper.activity.DanhBaActivity;
import com.example.ledoa.dailyexsuper.activity.LoginActivity;
import com.example.ledoa.dailyexsuper.activity.NhanTinActivity;
import com.example.ledoa.dailyexsuper.activity.ThemBanActivity;
import com.example.ledoa.dailyexsuper.activity.ThongBaoActivity;
import com.example.ledoa.dailyexsuper.adapter.LeftAdapter;
import com.example.ledoa.dailyexsuper.adapter.MainFragmentAdapter;
import com.example.ledoa.dailyexsuper.caches.ImageLoaderUtil;
import com.example.ledoa.dailyexsuper.connection.response.ChatResponse;
import com.example.ledoa.dailyexsuper.socketio.MySocket;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Chat;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemMenuLeft;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.UserPref;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int mIconMenuLeft[] = {R.drawable.icon_thongbao, R.drawable.icon_tab_timban_nau, R.drawable.icon_danhba,
            R.drawable.icon_caidat, R.drawable.icon_logout};
    String mTextMenuLeft[] = {"Thông báo mới","Thêm bạn", "Danh bạ","Cài đặt riêng tư","Thoát"};

    TextView mTvTabLuyenTap, mTvTabLichTap, mTvTabBaiTap, mTvTabMangXaHoi, mTvActionBarTitle, mTvUsernameMenuLeft;
    ImageView mIvTabLuyenTap, mIvTabLichTap, mIvTabBaiTap, mIvTabMangXaHoi, mIvTabMenuLeft, mIvAvatarMenuLeft;
    RelativeLayout mRlTabLuyenTap, mRlTabLichTap, mRlTabBaiTap, mRlTabMangXaHoi;
    Intent mIntentNhanTin, mIntentThongbao, mIntentDanhBa, mIntentThemBan, mIntentCaiDat, mIntenLogin;

    MainFragmentAdapter adapter;
    ItemMenuLeft mItemMenuLeft;
    List<ItemMenuLeft> mListDrawer;
    DrawerLayout mDlMenuLeft;
    LinearLayout mLvNhanTin;
    LeftAdapter adapterLeft;
    ListView mLvMenuLeft;
    ViewPager viewPager;
    UserPref mUserPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        MainApplication.setMySocket(new MySocket(this));
        MainApplication.getMySocket().connectSocket();
        mUserPref = new UserPref();

        DatabaseHandle databaseHandle = new DatabaseHandle(this);
        databaseHandle.autoInsertDataBase();

        attachMenuHeader();
        attachMenu();
        attachFragment();
        attachTab();
    }

    public void attachMenuHeader(){
        mTvUsernameMenuLeft = (TextView)findViewById(R.id.tvUsernameMenuLeft);
        mIvAvatarMenuLeft = (ImageView)findViewById(R.id.ivAvatarMenuLeft);

        if (mUserPref.getUser().avatar != null) {
            ImageLoaderUtil.display(mUserPref.getUser().avatar, mIvAvatarMenuLeft);
        } else {
            mIvAvatarMenuLeft.setImageResource(R.drawable.avt);
        }
        mTvUsernameMenuLeft.setText(mUserPref.getUser().username);

        mIvAvatarMenuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public void attachMenu() {
        mIntentThongbao = new Intent(MainActivity.this, ThongBaoActivity.class);
        mIntentThemBan = new Intent(MainActivity.this, ThemBanActivity.class);
        mIntentDanhBa = new Intent(MainActivity.this, DanhBaActivity.class);
        mIntenLogin = new Intent(MainActivity.this, LoginActivity.class);
        mIntentCaiDat = new Intent(MainActivity.this, MapsActivity.class);
        mIntentNhanTin = new Intent(MainActivity.this, NhanTinActivity.class);

        mTvActionBarTitle = (TextView) findViewById(R.id.actionbar_tvTitile);

        mDlMenuLeft = (DrawerLayout)findViewById(R.id.drawer_layout);
        mLvNhanTin = (LinearLayout)findViewById(R.id.lvNhanTin);
        mLvMenuLeft = (ListView)findViewById(R.id.left_drawer);
        mListDrawer = new ArrayList<ItemMenuLeft>();
        mUserPref = new UserPref();


        for(int i=0; i< mTextMenuLeft.length; i++){
            mItemMenuLeft = new ItemMenuLeft();
            mItemMenuLeft.setImage(mIconMenuLeft[i]);
            mItemMenuLeft.setText(mTextMenuLeft[i]);
            mListDrawer.add(mItemMenuLeft);
        }
        adapterLeft = new LeftAdapter(this, R.layout.custom_layout, mListDrawer);
        mLvMenuLeft.setAdapter(adapterLeft);
        mLvMenuLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: startActivity(mIntentThongbao); break;
                    case 1: startActivity(mIntentThemBan); break;
                    case 2: startActivity(mIntentDanhBa); break;
                    case 3: startActivity(mIntentCaiDat); break;
                    case 4:
                        mUserPref.setUser(null);
                        MainApplication.getMySocket().disconnectSocket();
                        startActivity(mIntenLogin);
                        break;
                }
            }
        });

        mLvNhanTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mIntentNhanTin);
            }
        });
    }

    public void onEvent(ChatResponse chatResponse) {

       /* Chat data = chatResponse.data;

        mList.add(data);
        buildTypeDisplay();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mLvMessage.smoothScrollToPosition(mList.size());
            }
        });*/

    }

    public void attachFragment(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
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

        mIvTabMenuLeft = (ImageView) findViewById(R.id.icon_tab_menu);
        mIvTabMenuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawerLayout.openDrawer(Gravity.START);
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