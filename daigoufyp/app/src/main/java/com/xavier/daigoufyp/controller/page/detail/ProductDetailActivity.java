package com.xavier.daigoufyp.controller.page.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetProductDetailRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.model.ProductPicture;
import com.xavier.daigoufyp.model.response.ProductResponse;
import com.xavier.daigoufyp.utils.TimerHelper;
import com.xavier.daigoufyp.view.product.ProductPictureView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_product_detail)
public class ProductDetailActivity extends AbsSpiceActivity {

    @InjectExtra(value = "product_id", optional = false)
    int product_id;

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

    TimerHelper timerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSpiceManger().execute(
                new GetProductDetailRequest(this, product_id),
                new GetProductDetailRequestListener());

        timerHelper = new TimerHelper(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                break;
        }
        return true;
    }

    public class GetProductDetailRequestListener extends AbsRequestListener<ProductResponse> {

        @Override
        public void onSuccess(ProductResponse productResponse) {
            productPictureView.bindModel(productResponse.product.product_pics, new ProductPictureView.ProductPictureListener() {
                @Override
                public void onPictureClick(ProductPicture productPicture) {

                }
            });
            productNameTextView.setText(productResponse.product.product_name);
            productCategoryTextView.setText(productResponse.product.category);
            productCountryTextView.setText(productResponse.product.country);
            productQuantityTextView.setText(productResponse.product.quantity);
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
            timerHelper.countDown(productResponse.product.product_end_time);
        }

        @Override
        public void onFailure(String msg) {

        }
    }
}
