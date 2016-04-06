package com.xavier.daigoufyp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 6/4/16.
 */
public class Order {
    @Expose
    public String seller_id;

    @Expose
    public String buyer_id;

    @Expose
    public long order_create_time;

    @Expose
    public String order_status;

    @Expose
    public long order_total_price;

    @Expose
    public long delivery_address;

    @Override
    public String toString() {
        return "Order{" +
                "seller_id='" + seller_id + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", order_create_time=" + order_create_time +
                ", order_status='" + order_status + '\'' +
                ", order_total_price=" + order_total_price +
                ", delivery_address=" + delivery_address +
                '}';
    }
}
