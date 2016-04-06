package com.xavier.daigoufyp.controller.page.abs;

import com.octo.android.robospice.SpiceManager;
import com.xavier.daigoufyp.controller.network.DaigouFypSerivce;

import roboguice.fragment.RoboFragment;

/**
 * Created by xavier on 23/3/16.
 */
public class AbsSpiceFragment extends RoboFragment {

    private static String TAG = "AbsSpiceFragment";

    private SpiceManager spiceManager = new SpiceManager(DaigouFypSerivce.class);

    public boolean isVisible = false;

    @Override
    public void onStart() {
        spiceManager.start(getContext());
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            isVisible = true;
        else
            isVisible = false;
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
