package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Order;

import java.util.List;

/**
 * Created by zensis on 20/4/16.
 */
public class MyOrderListResponse extends AbsResponse{
    @Expose
    public List<Order> orders;
}
