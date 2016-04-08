package com.xavier.daigoufyp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by xavier on 6/4/16.
 */
public class Shop {
    private static String TAG = "Shop";

    @Expose
    public int shop_id;

    @Expose
    public String shop_name;

    @Expose
    public String shop_description;

    @Expose
    public long shop_create_time;

    @Expose
    public String shop_pic;

    @Expose
    public String category;

    @Override
    public String toString() {
        return "Shop{" +
                "shop_id=" + shop_id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_description='" + shop_description + '\'' +
                ", shop_create_time=" + shop_create_time +
                ", shop_pic='" + shop_pic + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
