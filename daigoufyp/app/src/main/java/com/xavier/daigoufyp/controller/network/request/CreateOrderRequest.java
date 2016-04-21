package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.utils.Utils;

/**
 * Created by xavier on 21/4/16.
 */
public class CreateOrderRequest extends AbsRequest<AbsResponse> {
    String seller_id, buyer_id, delivery_address;
    int product_id, order_total_price;

    public CreateOrderRequest(Context context, String seller_id, String buyer_id,
                              String delivery_address, int product_id, int order_total_price) {
        super(context, AbsResponse.class);
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.delivery_address = delivery_address;
        this.product_id = product_id;
        this.order_total_price = order_total_price;
    }

    @Override
    public AbsResponse loadDataFromNetwork() throws Exception {
        return getService().createOrder(seller_id, buyer_id, product_id,
                "Pending", Utils.getCurrentTimeMilliSeconds(), order_total_price,
                delivery_address);
    }
}
