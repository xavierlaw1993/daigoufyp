package com.xavier.daigoufyp.controller;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by zensis on 6/4/16.
 */
public class DagiouFypApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
