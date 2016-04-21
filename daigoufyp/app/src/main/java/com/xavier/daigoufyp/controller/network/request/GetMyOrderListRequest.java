package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.MyOrderListResponse;

/**
 * Created by xavier on 21/4/16.
 */
public class GetMyOrderListRequest extends AbsRequest<MyOrderListResponse> {
    String order_type;

    public GetMyOrderListRequest(Context context, String order_type) {
        super(context, MyOrderListResponse.class);
        this.order_type = order_type;
    }

    @Override
    public MyOrderListResponse loadDataFromNetwork() throws Exception {
        return getService().getUserOrders(user.user_id, order_type);
    }
}
