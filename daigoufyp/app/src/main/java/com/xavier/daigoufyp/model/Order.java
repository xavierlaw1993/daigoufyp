package com.xavier.daigoufyp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by xavier on 6/4/16.
 */
public class Order {
    @Expose
    public String order_id;

    @Expose
    public String seller_id;

    @Expose
    public String seller_name;

    @Expose
    public String buyer_id;

    @Expose
    public String buyer_name;

    @Expose
    public String order_create_time;

    @Expose
    public String order_status;

    @Expose
    public String order_total_price;

    @Expose
    public String delivery_address;

    @Expose
    public Product product;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", seller_id='" + seller_id + '\'' +
                ", seller_name='" + seller_name + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", buyer_name='" + buyer_name + '\'' +
                ", order_create_time='" + order_create_time + '\'' +
                ", order_status='" + order_status + '\'' +
                ", order_total_price='" + order_total_price + '\'' +
                ", delivery_address='" + delivery_address + '\'' +
                ", product=" + product +
                '}';
    }
}
