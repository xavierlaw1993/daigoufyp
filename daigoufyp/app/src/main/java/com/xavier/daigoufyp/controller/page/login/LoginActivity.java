package com.xavier.daigoufyp.controller.page.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.controller.page.home.MainActivity;
import com.xavier.daigoufyp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AbsSpiceActivity {
    private static String TAG = "LoginActivity";

    @InjectView(R.id.facebookLoginButton)
    Button facebookLoginButton;

    CallbackManager callbackManager;

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v(TAG, response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        Log.v(TAG, "bFacebookData.toString() " + bFacebookData.toString());
                        User newUser = new User(LoginActivity.this);
                        try {
                            newUser.user_id = bFacebookData.getString("facebook_id");
                            newUser.user_name = bFacebookData.getString("first_name").concat(" ").concat(bFacebookData.getString("last_name"));
                            newUser.user_email = bFacebookData.getString("email");
                            newUser.user_phone = bFacebookData.getString("phone");
                            user.updateUser(newUser).commit();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email, gender"); // Parameter request
                request.setParameters(parameters);
                request.executeAsync();
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

    public void onFacebookLoginClick(View v) {
        Log.v(TAG, "onFacebookClick");
        List<String> permissions = new ArrayList<>();
        permissions.add("email");
        permissions.add("public_profile");
        LoginManager.getInstance().logInWithReadPermissions(this, permissions);
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            bundle.putString("facebook_id", id);

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            return bundle;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
