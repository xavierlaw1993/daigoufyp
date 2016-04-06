package com.xavier.daigoufyp.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xavier.daigoufyp.utils.Constants;

import roboguice.RoboGuice;

/**
 * Created by xavier on 21/3/16.
 */

@Singleton
public class User {

    @Inject
    SharedPreferences sharedPreferences;

    @Expose
    public String user_id;

    @Expose
    public String user_email;

    @Expose
    public String user_name;

    @Expose
    public String user_profile_pic;

    @Expose
    public String user_phone;

    @Override
    public String toString() {
        return "User{" +
                "sharedPreferences=" + sharedPreferences +
                ", user_id='" + user_id + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_profile_pic='" + user_profile_pic + '\'' +
                ", user_phone='" + user_phone + '\'' +
                '}';
    }

    @Inject
    public User(Context context) {
        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
        try {
            Gson gson = new Gson();
            User savedUser = gson.fromJson(sharedPreferences.getString(Constants.PREF_USER, null), User.class);
            user_id = savedUser.user_id;
            user_email = savedUser.user_email;
            user_name = savedUser.user_name;
            user_profile_pic = savedUser.user_profile_pic;
            user_phone = savedUser.user_phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User updateUser(User user) {
        user_id = user.user_id;
        user_email = user.user_email;
        user_name = user.user_name;
        user_profile_pic = user.user_profile_pic;
        user_phone = user.user_phone;
        return this;
    }

    public User clearUser() {
        user_id = null;
        user_email = null;
        user_name = null;
        user_profile_pic = null;
        user_phone = null;
        return this;
    }

    public void commit() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        sharedPreferences.edit()
                .putString(Constants.PREF_USER, gson.toJson(this))
                .commit();
    }

}
