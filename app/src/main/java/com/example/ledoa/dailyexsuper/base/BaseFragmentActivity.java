package com.example.ledoa.dailyexsuper.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import java.util.Stack;

abstract public class BaseFragmentActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener {

    private static final String TAG = BaseFragmentActivity.class.getSimpleName();

    protected final Stack<String> mFragmentTagStack = new Stack<>();

    abstract protected Fragment onCreateMainFragment(Bundle savedInstanceState);

    abstract protected int getFragmentContainerId();

    protected View mActionBar;

    abstract public void attachActionBar(int actionBarLayout);

    public interface OnBackPressListener {
        boolean onBackPress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(onCreateMainFragment(null));

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    public void showFragment(Fragment f) {
        String tag = f.getClass().getName() + getNextPositionOfFragment(f.getClass().getName());
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        if (mFragmentTagStack.size() > 0) {
            final Fragment ff = fm.findFragmentByTag(mFragmentTagStack.peek());
            ft.hide(ff);
        }
        ft.add(getFragmentContainerId(), f, tag);
        ft.show(f);
        ft.addToBackStack(tag);
        ft.commit();
        mFragmentTagStack.add(tag);
//        printStack();
    }

    public void showFragmentWithClearStack(Fragment f) {
        getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentTagStack.clear();
        showFragment(f);
    }

    /*
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (mFragmentTagStack.size() > 1) {
            Fragment f = fm.findFragmentByTag(mFragmentTagStack.peek());
            if (f instanceof OnBackPressListener) {
                if (((OnBackPressListener) f).onBackPress()) {
                    return;
                }
            }
        }
        super.onBackPressed();
    }
    */

    @Override
    public void onBackStackChanged() {
        android.app.FragmentManager fm = getFragmentManager();

        if (fm.getBackStackEntryCount() == mFragmentTagStack.size()) {
            return;
        }

        if (mFragmentTagStack.size() > 1) {
            android.app.FragmentTransaction ft = fm.beginTransaction();
            String tag = mFragmentTagStack.pop();
            if (fm.findFragmentByTag(tag) != null) {
                ft.remove(fm.findFragmentByTag(tag));
            }
            ft.commit();
        }
    }

    private int getNextPositionOfFragment(String tag) {
        int pos = 0;
        if (mFragmentTagStack.size() > 0) {
            for (String stackTag : mFragmentTagStack) {
                if (stackTag.contains(tag)) {
                    pos++;
                }
            }
        }
        return pos;
    }

    private void printStack() {
        Log.d(TAG, "=============================");
        if (mFragmentTagStack.size() > 0) {
            for (String stack : mFragmentTagStack) {
                Log.d(TAG, stack);
            }
        }
    }
}
