package com.xavier.daigoufyp.controller.page.abs;

import com.octo.android.robospice.SpiceManager;
import com.xavier.daigoufyp.controller.network.DaigouFypSerivce;

import roboguice.activity.RoboActionBarActivity;

/**
 * Created by xavier on 21/3/16.
 */
public class AbsSpiceActivity extends RoboActionBarActivity {
    private static String TAG = "AbsSpiceActivity";

    private SpiceManager spiceManger = new SpiceManager(DaigouFypSerivce.class);

    @Override
    protected void onStart() {
        spiceManger.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManger.shouldStop();
        super.onStop();
    }

    public SpiceManager getSpiceManger() {
        return spiceManger;
    }
}
