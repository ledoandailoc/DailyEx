package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ledoa.dailyexsuper.MainActivity;
import com.example.ledoa.dailyexsuper.util.UserPref;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserPref userPref = new UserPref();

        if (true) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}