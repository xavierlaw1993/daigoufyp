package com.xavier.daigoufyp.controller.page.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.home.MainActivity;
import com.xavier.daigoufyp.model.User;
import com.xavier.daigoufyp.utils.Utils;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity {
    private static String TAG = "SplashActivity";

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getScreenSize(this);
        Log.v(TAG, "user " + user.toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent();
                /**
                 * TODO: need to uncomment
                 */
                if (TextUtils.isEmpty(user.user_id))
                    i.setClass(SplashActivity.this, LoginActivity.class);
                else
                    i.setClass(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
