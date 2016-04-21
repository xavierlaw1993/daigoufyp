package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.ShopListResponse;

/**
 * Created by xavier on 21/4/16.
 */
public class GetAllShopsRequest extends AbsRequest<ShopListResponse> {

    public GetAllShopsRequest(Context context) {
        super(context, ShopListResponse.class);
    }

    @Override
    public ShopListResponse loadDataFromNetwork() throws Exception {
        return getService().getAllShops();
    }
}
