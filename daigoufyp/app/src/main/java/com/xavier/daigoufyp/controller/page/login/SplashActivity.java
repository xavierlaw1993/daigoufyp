package com.xavier.daigoufyp.controller.page.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xavier.daigoufyp.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
