package com.xavier.daigoufyp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xavier.daigoufyp.R;

import java.text.DecimalFormat;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 19/4/16.
 */
public class TimeChooserView extends LinearLayout {
    private static final String TAG = "TimeChooserView";

    Context context;

    @InjectView(R.id.weekEditText)
    EditText weekEditText;

    @InjectView(R.id.dayEditText)
    EditText dayEditText;

    @InjectView(R.id.hourEditText)
    EditText hourEditText;

    DecimalFormat decimalFormat = new DecimalFormat("##");

    private OnTimeChangeListener listener;

    public TimeChooserView(Context context) {
        super(context);
        init(context);
    }

    public TimeChooserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeChooserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimeChooserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_time_chooser, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);

        weekEditText.setText("00");
        dayEditText.setText("00");
        hourEditText.setText("00");
        weekEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Log.v(TAG, "s.toString() " + s.toString());
                    weekEditText.setText(decimalFormat.format(Integer.parseInt(s.toString())));
                    if (TextUtils.isEmpty(s.toString()))
                        weekEditText.setText("00");
                    if (listener != null)
                        listener.onWeekChange(Integer.parseInt(s.toString()));
                } catch (Exception ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dayEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    dayEditText.setText(decimalFormat.format(Integer.parseInt(s.toString())));
                    if (TextUtils.isEmpty(s.toString()))
                        dayEditText.setText("00");
                    if (listener != null)
                        listener.onDayChange(Integer.parseInt(s.toString()));
                } catch (Exception ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        hourEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    hourEditText.setText(decimalFormat.format(Integer.parseInt(s.toString())));
                    if (Integer.parseInt(s.toString()) > 23)
                        hourEditText.setText("23");
                    if (TextUtils.isEmpty(s.toString()))
                        hourEditText.setText("00");
                    if (listener != null)
                        listener.onHourChange(Integer.parseInt(s.toString()));
                } catch (Exception ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setOnTimeChangeListener(OnTimeChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTimeChangeListener {
        void onWeekChange(int week);

        void onDayChange(int day);

        void onHourChange(int hour);
    }
}
