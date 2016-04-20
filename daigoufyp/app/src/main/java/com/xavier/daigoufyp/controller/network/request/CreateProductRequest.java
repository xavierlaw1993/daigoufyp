package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.AbsResponse;

/**
 * Created by zensis on 20/4/16.
 */
public class CreateProductRequest extends AbsRequest<AbsResponse> {
    String product_name, product_description, category, country, remark;
    long product_create_time, product_end_time;
    int product_price, quantity;

    public CreateProductRequest(Context context, String product_name, String product_description,
                                String category, String country, String remark,
                                long product_create_time, long product_end_time,
                                int product_price, int quantity) {
        super(context, AbsResponse.class);
        this.product_name = product_name;
        this.product_description = product_description;
        this.category = category;
        this.country = country;
        this.remark = remark;
        this.product_create_time = product_create_time;
        this.product_end_time = product_end_time;
        this.product_price = product_price;
        this.quantity = quantity;
    }

    @Override
    public AbsResponse loadDataFromNetwork() throws Exception {
        return getService().createProduct(user.user_id,
                product_name, product_description, product_create_time, product_end_time,
                product_price, category, country, quantity, remark);
    }
}
