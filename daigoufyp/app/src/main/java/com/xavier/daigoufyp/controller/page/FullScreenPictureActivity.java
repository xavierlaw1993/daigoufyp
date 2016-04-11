package com.xavier.daigoufyp.controller.page;

import android.os.Bundle;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_full_screen_picture)
public class FullScreenPictureActivity extends AbsSpiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_picture);
    }
}
