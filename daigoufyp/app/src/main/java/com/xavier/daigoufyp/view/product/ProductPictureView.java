package com.xavier.daigoufyp.view.product;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.pager.ProductPicturePagerAdapter;
import com.xavier.daigoufyp.model.ProductPicture;

import java.util.List;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 11/4/16.
 */
public class ProductPictureView extends FrameLayout {
    Context context;

    @InjectView(R.id.pictureViewPager)
    ViewPager pictureViewPager;


    public ProductPictureView(Context context) {
        super(context);
        init(context);
    }

    public ProductPictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductPictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProductPictureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_prdouct_picture, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(List<ProductPicture> productPictureList, ProductPictureListener listener) {
        this.listener = listener;
        ProductPicturePagerAdapter adapter = new ProductPicturePagerAdapter(context, productPictureList, listener);
        pictureViewPager.setAdapter(adapter);
    }

    private ProductPictureListener listener;

    public interface ProductPictureListener {
        void onPictureClick(ProductPicture productPicture);
    }
}
