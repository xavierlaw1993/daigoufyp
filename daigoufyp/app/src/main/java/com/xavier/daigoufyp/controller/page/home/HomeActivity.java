package com.xavier.daigoufyp.controller.page.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.controller.page.login.LoginActivity;
import com.xavier.daigoufyp.model.User;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_home)
public class HomeActivity extends AbsSpiceActivity {

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
    }

    public void onLogoutClick(View v){
        LoginManager.getInstance().logOut();
        user.clearUser().commit();
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
