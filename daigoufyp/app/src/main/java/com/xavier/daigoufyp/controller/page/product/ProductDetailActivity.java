package com.xavier.daigoufyp.controller.page.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetProductDetailRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.ProductPicture;
import com.xavier.daigoufyp.model.response.ProductResponse;
import com.xavier.daigoufyp.utils.TimerHelper;
import com.xavier.daigoufyp.utils.Utils;
import com.xavier.daigoufyp.view.product.ProductPictureView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_product_detail)
public class ProductDetailActivity extends AbsSpiceBackActivity {

    @InjectExtra(value = "product_id", optional = false)
    int product_id;

    @InjectView(R.id.userNameTextView)
    TextView userNameTextView;

    @InjectView(R.id.userIconImageView)
    ImageView userIconImageView;

    @InjectView(R.id.productPictureView)
    ProductPictureView productPictureView;

    @InjectView(R.id.productNameTextView)
    TextView productNameTextView;

    @InjectView(R.id.productCategoryTextView)
    TextView productCategoryTextView;

    @InjectView(R.id.productCountryTextView)
    TextView productCountryTextView;

    @InjectView(R.id.productQuantityTextView)
    TextView productQuantityTextView;

    @InjectView(R.id.productDurationTextView)
    TextView productDurationTextView;

    @InjectView(R.id.productDescriptionTextView)
    TextView productDescriptionTextView;

    @InjectView(R.id.productRemarkTextView)
    TextView productRemarkTextView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @InjectView(R.id.contentLinearLayout)
    LinearLayout contentLinearLayout;

    @InjectView(R.id.wantToBuyProductButton)
    Button wantToBuyProductButton;

    TimerHelper timerHelper;

    Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressbar.setVisibility(View.VISIBLE);

        getSpiceManger().execute(
                new GetProductDetailRequest(this, product_id),
                new GetProductDetailRequestListener());

        timerHelper = new TimerHelper(this);
    }

    public class GetProductDetailRequestListener extends AbsRequestListener<ProductResponse> {

        @Override
        public void onSuccess(ProductResponse productResponse) {
            mProduct = productResponse.product;
            progressbar.setVisibility(View.GONE);
            wantToBuyProductButton.setVisibility(View.VISIBLE);
            contentLinearLayout.setVisibility(View.VISIBLE);

            ViewGroup.LayoutParams params = productPictureView.getLayoutParams();
            params.height = (int) (Utils.screenWidth * Utils.hdRatio);
            productPictureView.setLayoutParams(params);

            productPictureView.bindModel(productResponse.product.product_pics, new ProductPictureView.ProductPictureListener() {
                @Override
                public void onPictureClick(ProductPicture productPicture) {
                }
            });
            userNameTextView.setText(productResponse.product.user_name);
            try{
                Picasso.with(ProductDetailActivity.this)
                        .load(productResponse.product.user_profile_pic)
                        .placeholder(R.mipmap.ic_launcher)
                        .resize(Utils.screenWidth /8, (int)(Utils.screenWidth /8 * Utils.hdRatio))
                        .into(userIconImageView);

            }catch (Exception ex) {
            }
            productNameTextView.setText(productResponse.product.product_name);
            productCategoryTextView.setText(productResponse.product.category);
            productCountryTextView.setText(productResponse.product.country);
            productQuantityTextView.setText("" + productResponse.product.quantity);
            productDescriptionTextView.setText(productResponse.product.product_description);
            productRemarkTextView.setText(productResponse.product.remark);
            timerHelper.setTimerHelperListener(new TimerHelper.TimerHelperListener() {
                @Override
                public void onTimerStarted(String startTime) {
                    productDurationTextView.setText(startTime);
                }

                @Override
                public void onTimerTicking(String tickTime) {
                    productDurationTextView.setText(tickTime);
                }

                @Override
                public void onTimerFinished() {
                    productDurationTextView.setText(getString(R.string.product__finished));
                }
            });
            timerHelper.countDown(Long.parseLong(productResponse.product.product_end_time));
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }

    public void onWantToBuyClick(View v) {
        Intent i = new Intent(this, ProductConfirmBuyActivity.class);
        i.putExtra("PRODUCT_OBJ", mProduct);
        startActivity(i);
    }
}
