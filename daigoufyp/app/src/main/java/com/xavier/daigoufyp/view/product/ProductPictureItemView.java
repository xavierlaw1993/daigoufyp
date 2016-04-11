package com.xavier.daigoufyp.view.product;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.model.ProductPicture;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 11/4/16.
 */
public class ProductPictureItemView extends FrameLayout {
    Context context;

    @InjectView(R.id.productPicImageView)
    ImageView productPicImageView;

    public ProductPictureItemView(Context context) {
        super(context);
        init(context);
    }

    public ProductPictureItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductPictureItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProductPictureItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_product_picture_item, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(ProductPicture productPicture){
        Picasso.with(context)
                .load(productPicture.product_pic_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(productPicImageView);
    }
}
