package com.xavier.daigoufyp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xavier.daigoufyp.R;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 15/4/16.
 */
public class CustomSpinnerView extends LinearLayout {
    private static final String TAG = "CustomSpinnerView";
    Context context;

    @InjectView(R.id.dropImageView)
    ImageView dropImageView;

    @InjectView(R.id.riseImageView)
    ImageView riseImageView;

    @InjectView(R.id.contentEditText)
    EditText contentEditText;

    private OnQuantityChangeListener listener;

    public CustomSpinnerView(Context context) {
        super(context);
        init(context);
    }

    public CustomSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomSpinnerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_custom_spinner, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(final int minValue, final int maxValue, boolean showAsSpinner) {
        contentEditText.setText("" + minValue);
        if (!showAsSpinner) {
            dropImageView.setVisibility(GONE);
            riseImageView.setVisibility(GONE);
        }
        riseImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "value=" + contentEditText.getEditableText().toString());
                int newValue = Integer.parseInt(contentEditText.getEditableText().toString()) + 1;
                contentEditText.setText("" + (newValue <= maxValue ? newValue : maxValue));
                if (listener != null)
                    listener.onQuantityChange(Integer.parseInt(contentEditText.getEditableText().toString()));
            }
        });
        dropImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "value=" + contentEditText.getEditableText().toString());
                int newValue = Integer.parseInt(contentEditText.getEditableText().toString()) - 1;
                contentEditText.setText("" + (newValue >= minValue ? newValue : minValue));
                if (listener != null)
                    listener.onQuantityChange(Integer.parseInt(contentEditText.getEditableText().toString()));
            }
        });
    }

    public String getText() {
        return contentEditText.getEditableText().toString();
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener listener) {
        this.listener = listener;
    }

    public interface OnQuantityChangeListener {
        void onQuantityChange(int value);
    }
}
