package com.xavier.daigoufyp.controller.page.detail;

import android.os.Bundle;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetProductDetailRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.model.ProductPicture;
import com.xavier.daigoufyp.model.response.ProductResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSpiceManger().execute(
                new GetProductDetailRequest(this, product_id),
                new GetProductDetailRequestListener());
    }

    public class GetProductDetailRequestListener extends AbsRequestListener<ProductResponse> {

        @Override
        public void onSuccess(ProductResponse productResponse) {
            productPictureView.bindModel(productResponse.product.product_pics, new ProductPictureView.ProductPictureListener() {
                @Override
                public void onPictureClick(ProductPicture productPicture) {

                }
            });
        }

        @Override
        public void onFailure(String msg) {

        }
    }
}
