package com.xavier.daigoufyp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xavier.daigoufyp.R;

import roboguice.RoboGuice;

/**
 * Created by zensis on 18/4/16.
 */
public class ChooseImageView extends RelativeLayout {
    Context context;
    private int chooserType;

    public ChooseImageView(Context context) {
        super(context);
        init(context);
    }

    public ChooseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public ChooseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChooseImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_custom_spinner, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    
}
