package com.xavier.daigoufyp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.xavier.daigoufyp.R;

/**
 * Created by zensis on 18/4/16.
 */
public class IntentActionDialog extends Dialog {
    Context context;

    Button toCameraActionButton, toGalleryActionButton;

    OnButtonClickListener listener;

    public IntentActionDialog(Context context) {
        super(context);
        init(context);
    }

    public IntentActionDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public IntentActionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.view_intent_action_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        toCameraActionButton = (Button) findViewById(R.id.toCameraActionButton);
        toGalleryActionButton = (Button) findViewById(R.id.toGalleryActionButton);
        toCameraActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null)
                    listener.onCameraActionClick();
            }
        });
        toGalleryActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null)
                    listener.onGalleryActionClick();
            }
        });
    }

    public IntentActionDialog setOnButtonClickListener(OnButtonClickListener l) {
        this.listener = l;
        return this;
    }

    public interface OnButtonClickListener {
        void onCameraActionClick();

        void onGalleryActionClick();
    }
}
