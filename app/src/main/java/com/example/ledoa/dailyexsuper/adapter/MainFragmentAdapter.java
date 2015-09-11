package com.example.ledoa.dailyexsuper.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ledoa.dailyexsuper.fragment.FragmentLuyenTapTuDo;
import com.example.ledoa.dailyexsuper.fragment.FragmentLichTap;
import com.example.ledoa.dailyexsuper.fragment.FragmentBaiTap;
import com.example.ledoa.dailyexsuper.fragment.FragmentMangXaHoi;

public class MainFragmentAdapter extends FragmentPagerAdapter {

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // TODO Auto-generated method stub
            switch (position) {
                case 0:
                    return new FragmentBaiTap();
                case 1:
                    return new FragmentLichTap();
                case 2:
                    return new FragmentLuyenTapTuDo();
                case 3:
                    return new FragmentMangXaHoi();
            }
            return null;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 4;
        }


}
