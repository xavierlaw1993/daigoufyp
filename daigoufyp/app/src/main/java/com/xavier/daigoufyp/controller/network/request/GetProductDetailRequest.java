package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.ProductResponse;

/**
 * Created by xavier on 7/4/16.
 */
public class GetProductDetailRequest extends AbsRequest<ProductResponse>{
    int productId;

    public GetProductDetailRequest(Context context, int productId) {
        super(context, ProductResponse.class);
        this.productId = productId;
    }

    @Override
    public ProductResponse loadDataFromNetwork() throws Exception {
        return getService().getProductDetail(productId);
    }
}
