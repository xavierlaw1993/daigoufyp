package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Order;

/**
 * Created by zensis on 20/4/16.
 */
public class MyOrderResponse extends AbsResponse {
    @Expose
    public Order order;
}
