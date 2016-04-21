package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.ShopResponse;

/**
 * Created by xavier on 21/4/16.
 */
public class GetShopDetailRequest extends AbsRequest<ShopResponse> {
    int shop_id;

    public GetShopDetailRequest(Context context, int shop_id) {
        super(context, ShopResponse.class);
        this.shop_id = shop_id;
    }

    @Override
    public ShopResponse loadDataFromNetwork() throws Exception {
        return getService().getShopDetail(shop_id);
    }
}
