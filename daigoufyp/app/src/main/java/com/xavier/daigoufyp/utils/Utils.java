package com.xavier.daigoufyp.utils;

import android.app.Activity;
import android.graphics.Point;
import android.support.design.widget.Snackbar;
import android.view.Display;
import android.view.View;

/**
 * Created by xavier on 21/3/16.
 */
public class Utils {
    public static int screenWidth = 0;
    public static int screenHeight = 0;
    public static double hdRatio = 0.5625;
    public static double imageRatio = 0.75;

    public static void getScreenSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public static long getCurrentTimeMilliSeconds() {
        return System.currentTimeMillis();
    }

    public static void showFailureSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
