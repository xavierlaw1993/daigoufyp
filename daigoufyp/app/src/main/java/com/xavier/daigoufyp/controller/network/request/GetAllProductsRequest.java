package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.ProductListResponse;

/**
 * Created by xavier on 7/4/16.
 */
public class GetAllProductsRequest extends AbsRequest<ProductListResponse> {
    public GetAllProductsRequest(Context context) {
        super(context, ProductListResponse.class);
    }

    @Override
    public ProductListResponse loadDataFromNetwork() throws Exception {
        return getService().getAllProducts();
    }
}
