package com.example.ledoa.dailyexsuper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ledoa.dailyexsuper.MainActivity;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.base.BaseFragmentActivity;
import com.example.ledoa.dailyexsuper.fragment.LoginFragment;
import com.example.ledoa.dailyexsuper.listener.LoginListener;

public class LoginActivity extends BaseFragmentActivity implements LoginListener {

    @Override
    protected Fragment onCreateMainFragment(Bundle savedInstanceState) {
        return LoginFragment.newInstance();
    }   

    @Override
    protected int getFragmentContainerId() {
        return R.id.sign_in_container;
    }

    @Override
    public void attachActionBar(int actionBarLayout) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

    }

    @Override
    public void onShowLoginFragment() {
        if (isExistLoginFragment()) {
            onBackPressed();
        } else {
            LoginFragment fragment = LoginFragment.newInstance();
            showFragment(fragment);
        }
    }

    @Override
    public void onLogged() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    private boolean isExistSignUpFragment = false;
    private boolean isExistLoginFragment = false;

    public boolean isExistLoginFragment() {
        return isExistLoginFragment;
    }

    public boolean isExistSignUpFragment() {
        return isExistSignUpFragment;
    }

    public void setIsExistSignUpFragment(boolean isExistSignUpFragment) {
        this.isExistSignUpFragment = isExistSignUpFragment;
    }

    public void setIsExistLoginFragment(boolean isExistLoginFragment) {
        this.isExistLoginFragment = isExistLoginFragment;
    }
}
