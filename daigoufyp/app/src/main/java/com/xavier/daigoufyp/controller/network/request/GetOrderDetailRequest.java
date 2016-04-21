package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.MyOrderResponse;

/**
 * Created by xavier on 21/4/16.
 */
public class GetOrderDetailRequest extends AbsRequest<MyOrderResponse>{
    int order_id;

    public GetOrderDetailRequest(Context context, int order_id) {
        super(context, MyOrderResponse.class);
        this.order_id = order_id;
    }

    @Override
    public MyOrderResponse loadDataFromNetwork() throws Exception {
        return getService().getOrderDetail(order_id);
    }
}
