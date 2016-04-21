package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.AbsResponse;

/**
 * Created by xavier on 21/4/16.
 */
public class UpdateOrderRequest extends AbsRequest<AbsResponse> {
    int order_id;
    String order_status;

    public UpdateOrderRequest(Context context, int order_id, String order_status) {
        super(context, AbsResponse.class);
        this.order_id = order_id;
        this.order_status = order_status;
    }

    @Override
    public AbsResponse loadDataFromNetwork() throws Exception {
        return getService().updateOrder(order_id, order_status);
    }
}
