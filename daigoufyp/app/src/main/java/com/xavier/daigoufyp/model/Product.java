package com.xavier.daigoufyp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by xavier on 6/4/16.
 */
public class Product {
    private static String TAG = "Product";

    @Expose
    public int product_id;

    @Expose
    public String product_name;

    @Expose
    public String product_pic_url;

    @Expose
    public String product_description;

    @Expose
    public long product_create_time;

    @Expose
    public long product_end_time;

    @Expose
    public double product_price;

    @Expose
    public int quantity;

    @Expose
    public String remark;

    @Expose
    public String category;

    @Expose
    public String country;

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_pic_url='" + product_pic_url + '\'' +
                ", product_description='" + product_description + '\'' +
                ", product_create_time=" + product_create_time +
                ", product_end_time=" + product_end_time +
                ", product_price=" + product_price +
                ", quantity=" + quantity +
                ", remark='" + remark + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
