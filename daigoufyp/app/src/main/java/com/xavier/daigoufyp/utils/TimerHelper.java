package com.xavier.daigoufyp.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.xavier.daigoufyp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by zensis on 8/4/16.
 */
public class TimerHelper {
    private static String TAG = "TimerHelper";

    CountDownTimer timer;
    Context context;
    TimerHelperListener listener;

    public TimerHelper(Context context) {
        this.context = context;
    }

    public void setTimerHelperListener(TimerHelperListener listener) {
        this.listener = listener;
    }

    public void countDown(long endTime) {
//        Log.v(TAG, "countDown " + "end " + endTime + " now " + Utils.getCurrentTimeMilliSeconds());
        long timeDiff = endTime - Utils.getCurrentTimeMilliSeconds();
        if (timeDiff > 0) {
//            Log.v(TAG, "countDown " + timeDiff);
            if (listener != null)
                listener.onTimerStarted(formattedTime(timeDiff));

            timer = new CountDownTimer(timeDiff, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
//                    Log.v(TAG, "onTick");
                    if (listener != null)
                        listener.onTimerTicking(formattedTime(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    if (listener != null)
                        listener.onTimerFinished();
                }
            }.start();
        } else {
            if (listener != null)
                listener.onTimerFinished();
        }
    }

    public String formattedTime(long time) {
        String formattedTime = "";
        NumberFormat f = new DecimalFormat("00");
        int day = (int) TimeUnit.MILLISECONDS.toDays(time);
        long hour = TimeUnit.MILLISECONDS.toHours(time) - (day * 24);
        long minute = TimeUnit.MILLISECONDS.toMinutes(time) - (TimeUnit.MILLISECONDS.toHours(time) * 60);
        long second = TimeUnit.MILLISECONDS.toSeconds(time) - (TimeUnit.MILLISECONDS.toMinutes(time) * 60);
        if (day > 1)
            formattedTime = day + " "
                    .concat(context.getString(R.string.product__days_unit) + " ")
                    .concat(f.format(hour) + ":")
                    .concat(f.format(minute) + ":")
                    .concat(f.format(second));
        else if (day > 0)
            formattedTime = day + " "
                    .concat(context.getString(R.string.product__day_unit) + " ")
                    .concat(f.format(hour) + ":")
                    .concat(f.format(minute) + ":")
                    .concat(f.format(second));
        else
            formattedTime = f.format(hour) + ":"
                    .concat(f.format(minute) + ":")
                    .concat(f.format(second));
        return formattedTime;
    }

    public interface TimerHelperListener {
        void onTimerStarted(String startTime);

        void onTimerTicking(String tickTime);

        void onTimerFinished();
    }
}
