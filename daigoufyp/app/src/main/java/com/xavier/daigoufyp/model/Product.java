package com.xavier.daigoufyp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by zensis on 6/4/16.
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
}
