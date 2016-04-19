package com.xavier.daigoufyp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
        contentEditText.setMaxEms(String.valueOf(maxValue).length());
        if (!showAsSpinner) {
            dropImageView.setVisibility(GONE);
            riseImageView.setVisibility(GONE);
        }
        riseImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.v(TAG, "value=" + contentEditText.getEditableText().toString());
                int newValue = Integer.parseInt(contentEditText.getEditableText().toString()) + 1;
                contentEditText.setText("" + (newValue <= maxValue ? newValue : maxValue));
            }
        });
        dropImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.v(TAG, "value=" + contentEditText.getEditableText().toString());
                int newValue = Integer.parseInt(contentEditText.getEditableText().toString()) - 1;
                contentEditText.setText("" + (newValue >= minValue ? newValue : minValue));
            }
        });
        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v(TAG, "onTextChanged value=" + contentEditText.getEditableText().toString());
                try {
                    if (TextUtils.isEmpty(contentEditText.getEditableText()))
                        contentEditText.setText("" + minValue);
                    if (listener != null)
                        listener.onQuantityChange(Integer.parseInt(contentEditText.getEditableText().toString()));
                } catch (Exception ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
