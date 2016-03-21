package com.xavier.daigoufyp.controller.page.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AbsSpiceActivity {
    private static String TAG = "LoginActivity";

    @InjectView(R.id.facebookLoginButton)
    LoginButton facebookLoginButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        List<String> permissions = new ArrayList<>();
        permissions.add("email");
        permissions.add("public_profile");
        facebookLoginButton.setReadPermissions(permissions);
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
//                Log.v(TAG, "profile_lastname " + profile.getLastName());
//                Log.v(TAG, "profile_firstname  " + profile.getFirstName());
                Log.v(TAG, "profile_id " + profile.getId());
            }

            @Override
            public void onCancel() {
                Log.v(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.v(TAG, "onError " + error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
