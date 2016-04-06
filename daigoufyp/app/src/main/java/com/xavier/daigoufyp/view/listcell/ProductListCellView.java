package com.xavier.daigoufyp.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.model.Product;

import roboguice.RoboGuice;

/**
 * Created by zensis on 6/4/16.
 */
public class ProductListCellView extends LinearLayout {
    Context context;

    public ProductListCellView(Context context) {
        super(context);
        init(context);
    }

    public ProductListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProductListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_product_item, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(Product product) {

    }
}
