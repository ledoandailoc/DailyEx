package com.example.ledoa.dailyexsuper.base;

import android.support.v4.app.Fragment;

abstract public class BaseFragment extends Fragment {

    abstract protected void initActionBarLayout();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initActionBarLayout();
        }

    }

}
