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
    public String userId;

    @Expose
    public String name;

    @Expose
    public String userPic;

    @Expose
    public String email;

    @Expose
    public String gender;

    @Expose
    public String phone;

    @Override
    public String toString() {
        return "User{" +
                "sharedPreferences=" + sharedPreferences +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", userPic='" + userPic + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Inject
    public User(Context context) {
        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
        try {
            Gson gson = new Gson();
            User savedUser = gson.fromJson(sharedPreferences.getString(Constants.PREF_USER, null), User.class);
            userId = savedUser.userId;
            name = savedUser.name;
            userPic = savedUser.userPic;
            phone = savedUser.phone;
            email = savedUser.email;
            gender = savedUser.gender;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User updateUser(User user) {
        userId = user.userId;
        name = user.name;
        userPic = user.userPic;
        phone = user.phone;
        email = user.email;
        gender = user.gender;
        return this;
    }

    public User clearUser() {
        userId = null;
        name = null;
        phone = null;
        email = null;
        gender = null;
        return this;
    }

    public void commit() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        sharedPreferences.edit()
                .putString(Constants.PREF_USER, gson.toJson(this))
                .commit();
    }

}
