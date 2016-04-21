package com.xavier.daigoufyp.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.model.Shop;
import com.xavier.daigoufyp.utils.Utils;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by xavier on 21/4/16.
 */
public class ShopListCellView extends LinearLayout {
    Context context;

    @InjectView(R.id.userIconImageView)
    ImageView userIconImageView;

    @InjectView(R.id.userNameTextView)
    TextView userNameTextView;

    @InjectView(R.id.shopNameTextView)
    TextView shopNameTextView;

    @InjectView(R.id.shopCategoryTextView)
    TextView shopCategoryTextView;

    @InjectView(R.id.shopPicImageView)
    ImageView shopPicImageView;

    public ShopListCellView(Context context) {
        super(context);
        init(context);
    }

    public ShopListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShopListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_shop_item, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(Shop shop) {
        userNameTextView.setText(shop.user_name);
        shopNameTextView.setText(shop.shop_name);
        shopCategoryTextView.setText(shop.category);

        ViewGroup.LayoutParams params = shopPicImageView.getLayoutParams();
        params.height = (int) (Utils.screenWidth * Utils.hdRatio);
        shopPicImageView.setLayoutParams(params);

        try {
            Picasso.with(context)
                    .load(shop.user_profile_pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(Utils.screenWidth / 8, (int) (Utils.screenWidth / 8 * Utils.hdRatio))
                    .into(userIconImageView);

            Picasso.with(context)
                    .load(shop.shop_pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .resize(Utils.screenWidth, (int) (Utils.screenWidth * Utils.hdRatio))
                    .into(shopPicImageView);
        } catch (Exception ex) {
        }

    }
}
