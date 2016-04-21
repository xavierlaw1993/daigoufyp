package com.xavier.daigoufyp.controller.page.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetShopDetailRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.controller.page.home.MainActivity;
import com.xavier.daigoufyp.model.response.ShopResponse;
import com.xavier.daigoufyp.utils.Utils;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_shop_detail)
public class ShopDetailActivity extends AbsSpiceBackActivity {

    @InjectView(R.id.addMoreProductImageView)
    ImageView addMoreProductImageView;

    @InjectView(R.id.userNameTextView)
    TextView userNameTextView;

    @InjectView(R.id.userIconImageView)
    ImageView userIconImageView;

    @InjectView(R.id.shopPicImageView)
    ImageView shopPicImageView;

    @InjectView(R.id.shopNameTextView)
    TextView shopNameTextView;

    @InjectView(R.id.shopCategoryTextView)
    TextView shopCategoryTextView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @InjectExtra(value = "SHOP_ID", optional = false)
    int shop_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSpiceManger().execute(
                new GetShopDetailRequest(this, shop_id),
                new GetShopDetailRequestListener());
    }

    private class GetShopDetailRequestListener extends AbsRequestListener<ShopResponse> {

        @Override
        public void onSuccess(ShopResponse shopResponse) {
            Log.v("xavier", shopResponse.shop.toString());
            progressbar.setVisibility(View.GONE);
            userNameTextView.setText(shopResponse.shop.user_name);
            try {
                Picasso.with(ShopDetailActivity.this)
                        .load(shopResponse.shop.user_profile_pic)
                        .placeholder(R.mipmap.ic_launcher)
                        .resize(Utils.screenWidth / 8, (int) (Utils.screenWidth / 8 * Utils.hdRatio))
                        .into(userIconImageView);
                Picasso.with(ShopDetailActivity.this)
                        .load(shopResponse.shop.shop_pic)
                        .placeholder(R.mipmap.ic_launcher)
                        .resize(Utils.screenWidth, (int) (Utils.screenWidth * Utils.hdRatio))
                        .into(shopPicImageView);
            } catch (Exception ex) {
            }
            shopNameTextView.setText(shopResponse.shop.shop_name);
            shopCategoryTextView.setText(shopResponse.shop.category);
            addMoreProductImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ShopDetailActivity.this, MainActivity.class);
                    i.putExtra("TO_FRAGMENT", R.id.nav_create_product);
                    startActivity(i);
                }
            });
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }
}
